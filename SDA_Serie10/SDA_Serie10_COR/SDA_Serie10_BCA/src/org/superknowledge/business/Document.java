package org.superknowledge.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.Year;
import java.util.*;

/**
 * Document. In our case, a document is used specifically in bibliographic coupling.
 * A document contains references that are sorted.
 * @author Maximiliano Jeanneret Medina
 * @version 0.0.2, 12/08/20
 */
public class Document extends UnitOfAnalysis {

    private String title;
    private List<String> authors;
    private List<String> authorKeywords;
    private List<String> databaseKeywords;
    private String documentAbstract;
    private int year;
    private String source;
    private String volume;
    private String issue;
    private String artNo;
    private String beginPage;
    private String endPage;
    private String doi;
    private String scopusLink;
    private String scopusCitedByLink;

    /**
     * References of this document.
     */
    private Map<String, Reference> references;

    public static final String NO_TITLE = "[no title]";
    public static final String NO_AUTHORS = "[no authors]";

    private static final String DOI_BASE_LINK = "https://doi.org/";

    public Document() {
        authors = new ArrayList<>();
        authorKeywords = new ArrayList<>();
        databaseKeywords = new ArrayList<>();
        references = new TreeMap<>();
    }

    /**
     * Add or update by following the map properties.
     * @param reference
     */
    public void putReference(Reference reference) {
        this.references.put(reference.getReferenceKey(), reference);
    }

    /**
     * Calculate the co-citation of two references by this document.
     * @param referenceOne
     * @param referenceTwo
     * @return 1 if the references are co-cited by this document, 0 if not.
     */
    public int areCoCited(Reference referenceOne, Reference referenceTwo){

        int areCoCited = 0;

        if(this.references.containsKey(referenceOne.getReferenceKey())){
            if(this.references.containsKey(referenceTwo.getReferenceKey())){
                areCoCited = 1;
            }
        }

        return areCoCited;
    }

    /**
     * Compare the bibliography of two documents.
     * When the documents share one reference, the index is incremented by one.
     * @param document Document references comparison
     * @return Bibliographic coupling index.
     */
    public int computeBibliographicCoupling(Document document){
        int bibliographiCoupling = 0;

        for(Map.Entry<String, Reference> entry : this.getReferences()){
            if(document.references.containsKey(entry.getValue().getReferenceKey()))
                bibliographiCoupling++;
        }

        return bibliographiCoupling;
    }

    public String getFirstAuthorWithYear(){
        String shortCit = "";

        if(this.authors != null && this.authors.size() > 0){
            shortCit = shortCit.concat(this.authors.get(0));
        }else{
            shortCit = "No author";
        }

        if(this.year != -1)
            shortCit = shortCit.concat("(").concat(String.valueOf(this.year)).concat(")");

        return shortCit;
    }

    public String getDoiUrl(){
        if(doi != null && doi.length() > 0){
            return DOI_BASE_LINK.concat(doi);
        }else{
            return "";
        }
    }

    public String getAuthorsString(){
        String authors = "";
        for(String author : this.authors){
            authors = authors.concat(author);
        }
        return authors;
    }

    public String getAuthorKeywordsString(){
        String keywords = "";
        for(String keyword : this.authorKeywords){
            keywords = keywords.concat(keyword);
        }
        return keywords;
    }

    public String getDatabaseKeywordsString(){
        String keywords = "";
        for(String keyword : this.databaseKeywords){
            keywords = keywords.concat(keyword);
        }
        return keywords;
    }

    public String getDescription(){
        String description = "";
        if(this.getAuthors().size() > 0)
            description = description.concat(this.getAuthorsString());

        if(this.getYear() != -1)
            description = description.concat("(").concat(String.valueOf(this.getYear())).concat(")");

        if(this.getTitle() != null && this.getTitle().length() > 0)
            description = description.concat(", ").concat(this.getTitle());

        if(this.getSource() != null && this.getSource().length() > 0)
            description = description.concat(", ").concat(this.getSource());

        if(this.getVolume() != null && this.getVolume().length() > 0)
            description = description.concat(", ").concat(this.getVolume());

        if(this.getIssue() != null && this.getIssue().length() > 0)
            description = description.concat(", ").concat(this.getIssue());

        return description;
    }

    @Override
    public String getLabel() {
        return getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        if(StringUtils.isBlank(this.title))
            this.title = NO_TITLE;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        checkYear(year);
        this.year = year;
    }

    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(String beginPage) {
        this.beginPage = beginPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getAuthorKeywords() {
        return authorKeywords;
    }

    public void setAuthorKeywords(List<String> authorKeywords) {
        this.authorKeywords = authorKeywords;
    }

    public List<String> getDatabaseKeywords() {
        return databaseKeywords;
    }

    public void setDatabaseKeywords(List<String> databaseKeywords) {
        this.databaseKeywords = databaseKeywords;
    }

    public Map<String, Reference> getMapReferences() {
        return references;
    }

    /**
     * An Set for better performance that creating a new List.
     * @return
     */
    public Set<Map.Entry<String, Reference>> getReferences() {
        return references.entrySet();
    }

    public Collection<Reference> getColReferences() {
        return references.values();
    }

    public int getNbReferences(){
        return references.size();
    }

    public String getScopusLink() {
        return scopusLink;
    }

    public void setScopusLink(String scopusLink) {
        this.scopusLink = scopusLink;
    }

    public String getScopusCitedByLink() {
        return scopusCitedByLink;
    }

    public void setScopusCitedByLink(String scopusCitedByLink) {
        this.scopusCitedByLink = scopusCitedByLink;
    }

    @Override
    public String toString(){
        return this.toShortString();
    }

    public String toShortString(){
        StringBuilder stb = new StringBuilder();
        stb.append("\n").append(this.getZeroBasedId());
        // stb.append(" (").append(hashCode()).append(")");
        stb.append(", ").append(this.title, 0, 30).append("...");
        stb.append(", ").append(this.year);
        if(getBibliometricMetric() != null) {
            stb.append(", cited by:").append(getBibliometricMetric().getRawCitationCount());
            stb.append(", connected nodes:").append(getBibliometricMetric().getNbConnectedNodes());
            stb.append(", total edge weight:").append(getBibliometricMetric().getTotalEdgeWeight());
        }
        return stb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        if (!super.equals(o)) return false;
        Document document = (Document) o;
        return year == document.year &&
                title.equals(document.title) &&
                doi.equals(document.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, year, doi);
    }

    private void checkYear(final int year){
        if(year < 1 || year > Year.now().getValue() + 10)
            throw new IllegalArgumentException("Year is invalid: " + year);
    }

    public static void checkNumeric(final String year){
        if(!NumberUtils.isParsable(year))
            throw new IllegalArgumentException("Document year is not parsable to an integer.");
    }

}
