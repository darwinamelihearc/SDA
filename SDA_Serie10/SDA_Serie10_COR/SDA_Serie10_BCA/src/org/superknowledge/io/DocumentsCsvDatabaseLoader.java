package org.superknowledge.io;

import org.superknowledge.business.BibliometricMetric;
import org.superknowledge.business.Document;
import org.superknowledge.business.Documents;
import org.superknowledge.business.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.superknowledge.business.Document.checkNumeric;
import static org.superknowledge.io.FileDatabaseUtils.findColumnIndex;

public class DocumentsCsvDatabaseLoader extends DocumentsDatabaseLoader {

    /**
     * Input
     * @param filepath Full path of the file that will be loaded.
     */
    public DocumentsCsvDatabaseLoader(final String filepath){
        super(filepath);
    }

    public void process() {
        this.documents = loadCleanDatabase(filepath);
        this.documents.setReferencesUniqueId();
    }

    /**
     * Load a clean database into a List<String[]>.
     * @param filename Document database filepath.
     */
    private Documents loadCleanDatabase(final String filename){
        List<String[]> cleanedDatabase = ParserUtils.parseCSV(filename);
        return parseDocumentsDatabase(cleanedDatabase);
    }

    /**
     * Parse a cleaned documents database.
     * Use Pattern.quote to parse "|" character.
     * @param cleanedDatabase Cleaned documents database.
     * @return List of documents.
     */
    private Documents parseDocumentsDatabase(List<String[]> cleanedDatabase){

        int titleColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.TITLE.getColumnName());
        int authorsColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.AUTHORS.getColumnName());
        int abstractColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.ABSTRACT.getColumnName());
        int yearColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.YEAR.getColumnName());
        int doiColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.DOI.getColumnName());
        int sourceColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.SOURCE_TITLE.getColumnName());
        int volumeColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.VOLUME.getColumnName());
        int issueColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.ISSUE.getColumnName());
        int pageStartColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.PAGE_START.getColumnName());
        int pageEndColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.PAGE_END.getColumnName());
        int authorKeywordsColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.AUTHOR_KEYWORDS.getColumnName());
        int indexKeywordsColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.INDEX_KEYWORDS.getColumnName());
        int citedByColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.CITED_BY.getColumnName());
        int referenceColumnIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.REFERENCES.getColumnName());
        int scopusLinkIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.SCOPUS_LINK.getColumnName());
        int scopusCitedByLinkIndex = findColumnIndex(cleanedDatabase.get(0), DocumentReferenceConstant.SCOPUS_CITED_BY_LINK.getColumnName());
        int notFoundColumnIndex = -1;

        int startIndex = 1; // Skip header
        int documentNum = 0;
        int referenceNum = 0;

        // The size is known in advance, prevent array list unnecessary grow
        Documents documents = new Documents(cleanedDatabase.size()-1);
        int citedBy;

        for (int i = startIndex; i < cleanedDatabase.size(); i++) {

            // Create a document
            Document document = new Document();
            document.setZeroBasedId(documentNum); // Generated id

            if(titleColumnIndex != notFoundColumnIndex) {
                document.setTitle(cleanedDatabase.get(i)[titleColumnIndex]);
            }

            if (yearColumnIndex != notFoundColumnIndex) {
                checkNumeric(cleanedDatabase.get(i)[yearColumnIndex]);
                document.setYear(Integer.parseInt(cleanedDatabase.get(i)[yearColumnIndex]));
            }

            if(doiColumnIndex != notFoundColumnIndex)
                document.setDoi(cleanedDatabase.get(i)[doiColumnIndex]);

            if(pageStartColumnIndex != notFoundColumnIndex)
                document.setBeginPage(cleanedDatabase.get(i)[pageStartColumnIndex]);

            if(pageEndColumnIndex != notFoundColumnIndex)
                document.setEndPage(cleanedDatabase.get(i)[pageEndColumnIndex]);

            if(volumeColumnIndex != notFoundColumnIndex)
                document.setVolume(cleanedDatabase.get(i)[volumeColumnIndex]);

            if(issueColumnIndex != notFoundColumnIndex)
                document.setIssue(cleanedDatabase.get(i)[issueColumnIndex]);

            if(sourceColumnIndex != notFoundColumnIndex)
                document.setSource(cleanedDatabase.get(i)[sourceColumnIndex]);

            if(abstractColumnIndex != notFoundColumnIndex)
                document.setDocumentAbstract(cleanedDatabase.get(i)[abstractColumnIndex]);

            if(authorsColumnIndex != notFoundColumnIndex) {
                String authors = cleanedDatabase.get(i)[authorsColumnIndex];
                document.setAuthors(parse(authors, ","));
            }

            if(authorKeywordsColumnIndex != notFoundColumnIndex) {
                String keywords = cleanedDatabase.get(i)[authorKeywordsColumnIndex];
                document.setAuthorKeywords(parse(keywords, Pattern.quote("|")));
            }

            if(indexKeywordsColumnIndex != notFoundColumnIndex) {
                String keywords = cleanedDatabase.get(i)[indexKeywordsColumnIndex];
                document.setDatabaseKeywords(parse(keywords, Pattern.quote("|")));
            }

            // Only store valid cited by value
            if(citedByColumnIndex != notFoundColumnIndex) {
                if(cleanedDatabase.get(i)[citedByColumnIndex] != null) {
                    citedBy = Integer.parseInt(cleanedDatabase.get(i)[citedByColumnIndex]);
                    BibliometricMetric metric = new BibliometricMetric();
                    try {
                        metric.setRawCitationCount(citedBy);
                    } catch (Exception ignored) { }
                    document.setBibliometricMetric(metric);
                }
            }

            if(scopusLinkIndex != notFoundColumnIndex)
                document.setScopusLink(cleanedDatabase.get(i)[scopusLinkIndex]);

            if(scopusCitedByLinkIndex != notFoundColumnIndex)
                document.setScopusCitedByLink(cleanedDatabase.get(i)[scopusCitedByLinkIndex]);

            if(referenceColumnIndex != notFoundColumnIndex) {
                // Parse all document references and store them into the document
                String references = cleanedDatabase.get(i)[referenceColumnIndex];
                if(references != null && references.length() > 0) {
                    // Important to trim at references and reference levels
                    String[] referencesArray = references.trim().split(";");
                    for (String referenceString : referencesArray) {
                        Reference reference = new Reference(referenceString);
                        reference.setZeroBasedId(referenceNum); // TODO Update because the id need to be generated from references list and not documents-reference list
                        document.putReference(reference);
                        referenceNum++;
                    }
                }
            }

            documents.addDocumentAndUniqueReferences(document); // Need to be done after all references inserted in the current document
            documentNum++;

        }

        return documents;
    }

    /**
     * Parse data by a separator.
     * @param data Data to parse
     * @param separator Separator
     * @return Parsed data
     */
    private static List<String> parse(final String data, final String separator){
        List<String> elements = new ArrayList<>();
        if(data != null && data.length() > 0) {
            for(String element : data.split(separator)){
                elements.add(element.trim());
            }
        }
        return elements;
    }

    public Documents getDocuments() {
        return documents;
    }

}
