package org.superknowledge.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reference of a document.
 * A reference is not associated to a document because the same reference can be shared by multiple documents.
 * @author Maximiliano Jeanneret Medina
 * @version 0.0.2, 12/08/20
 * @see {Effective Java : Item 11: Always override hashCode when you override equals}
 */
public class Reference extends UnitOfAnalysis {

    /**
     * Complete string of the reference.
     */
    private final String referenceKey;

    // hashCode method with lazily initialized cached hash code
    // private int hashCode; // Automatically initialized to 0

    public Reference(String referenceKey){
        super();
        this.referenceKey = referenceKey;
    }

    public int getZeroBasedId() {
        return zeroBasedId;
    }

    public void setZeroBasedId(int zeroBasedId) {
        this.zeroBasedId = zeroBasedId;
    }

    public String getReferenceKey() {
        return this.referenceKey;
    }

    @Override
    public String getLabel() {
        return getReferenceKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference)) return false;
        if (!super.equals(o)) return false;
        Reference reference = (Reference) o;
        return referenceKey.equals(reference.referenceKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), referenceKey);
    }

    // Print

    public String toShortString(){
        StringBuilder stb = new StringBuilder();
        stb.append(this.getZeroBasedId());
        stb.append(" - ");
        if(this.referenceKey.length() >= 20) {
            stb.append(this.referenceKey.substring(0, 20));
        }else{
            stb.append(this.referenceKey);
        }
        stb.append(" (").append(hashCode()).append(")");
        stb.append(" - ");
        stb.append(this.retainedInAnalyis);
        stb.append(" - ");
        if(getBibliometricMetric() != null) {
            stb.append(getBibliometricMetric().getRawCitationCount());
        }
        return stb.toString();
    }


    /**
     * Get the title from the complete reference string.
     * @param reference Complete reference string
     * @return Title
     * @deprecated Legacy code from Matrices.
     */
    public static String getTitlePart(String reference){
        String R1 = "(?i)(?u)(\\((\\d){4}\\)([^\\.]+)\\.)";
        String R2 = "(?i)(?u)(\\.([^\\.]+)\\((\\d){4}\\))";
        int nbcar = (int) (((double)reference.length())/3);
        String R3 = "(?i)(?u)(\\.[^\\.]{10,}\\.)";

        Pattern pR1 = Pattern.compile(R1);
        Matcher mR1 = pR1.matcher(reference);

        String T1="";
        String T2="";

        if (mR1.find()) {
            String r = mR1.group(0).toString().trim();
            T1 =  r.substring(7);
        }

        Pattern pR2 = Pattern.compile(R2);
        Matcher mR2 = pR2.matcher(reference);
        if (mR2.find()) {
            String r = mR2.group(0).toString().trim();
            T2 = r.substring(1,r.length()-7);
        }

        if ((T1.length()<nbcar)&&(T2.length()<nbcar)){
            Pattern pR3 = Pattern.compile(R3);
            Matcher mR3 = pR3.matcher(reference);
            if (mR3.find()) {
                String r = mR3.group(0).toString().trim();
                return r.substring(1,r.length()-1).trim();
            } else {
                return "";
            }
        } else
        if(T1.length()> T2.length())
            return T1;
        else
            return T2;
    }

    private final static String regExpAuthor = "(?i)(?u)[ ]*([\\w]*)(,|[ ])[ ]*";
    private final static String regExpYear = "(?i)(?u)(1[5-9][0-9]{2}|20[0-9]{2})";

    private final static Pattern patAuthor = Pattern.compile(regExpAuthor);
    private final static Pattern patYear = Pattern.compile(regExpYear);

    /**
     * Get author and year like APA citation style in a full reference.
     * @see {https://docs.oracle.com/javase/tutorial/essential/regex/matcher.html}
     * @param reference
     * @return
     */
    public static String getAuthorAndYear(String reference){

        Matcher matAuthor = patAuthor.matcher(reference);

        List<String> refWithAuthors = new ArrayList<>();
        String authorsPart = "";
        String yearPart = "";

        String part;
        while(matAuthor.find()) {
            part = reference.substring(matAuthor.start(), matAuthor.end());
            part = part.trim().replaceAll("[^a-zA-Z ]", "");
            refWithAuthors.add(part);
        }

        // Create authors part
        authorsPart = (!refWithAuthors.isEmpty()) ? refWithAuthors.get(0) : Document.NO_AUTHORS;
        // Get next author part
        if(refWithAuthors.size() > 1) {
            String nextPart = refWithAuthors.get(1);
            if (refWithAuthors.get(0).length() <= 3 && nextPart.length() > 1)
                authorsPart += " " + refWithAuthors.get(1);
        }

        Matcher matYear = patYear.matcher(reference);
        if (matYear.find()) {
            yearPart = matYear.group(0).trim();
        }

        return authorsPart  + " (" + yearPart  + ")";
    }

}
