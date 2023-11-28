package org.superknowledge.services.cooccurrence;

import org.superknowledge.business.CoOccurrenceMatrix;
import org.superknowledge.business.Document;
import org.superknowledge.business.Documents;
import org.superknowledge.business.UnitOfAnalysis;

import java.util.*;

/**
 * Process a document bibliographic coupling analysis.
 * The attribute {@code citationThreshold} serves as constraints to filter documents.
 * Theoretical details on these constraints can be found in Walsh, I., & Renaud, A. (2017). Reviewing the literature in the IS field: Two bibliometric techniques to guide readings and help the interpretation of the literature. Systèmes d’information & management (Vol. 22). https://doi.org/10.3917/sim.173.0075
 * @author Maximiliano Jeanneret Medina
 */
public class DBCAProcessor extends CoOccurrenceProcessor<Document> {

    public DBCAProcessor(Documents documents,
                         double citationThreshold) {
        super(documents, citationThreshold);
    }

    /**
     * Filter the header table by selecting only the documents above the threshold.
     * @param citationThreshold
     * @return
     */
    @Override
    public List<Document> filter(double citationThreshold) {
        List<Document> retainedDocuments = new ArrayList<>();
        int i = 0;
        while (i < this.documents.getDocuments().size() &&
                this.documents.getDocuments().get(i).getBibliometricMetric().getRawCitationCount() >= citationThreshold) {
            this.documents.getDocuments().get(i).setRetainedInAnalyis(true); // References retained in the second order
            this.documents.getDocuments().get(i).setZeroBasedId(i); // Zero-based index
            retainedDocuments.add(this.documents.getDocuments().get(i));
            i++;
        }
        return retainedDocuments;
    }

    /**
     * Sort documents by ascendant alphanumeric order.
     * Regenerate unique zero-based id.
     * @implNote Comparing titles need non-null titles.
     */
    @Override
    protected void sortByName(List<Document> documents) {
        documents.sort(Comparator.comparing(Document::getTitle));
        int zeroBasedId = 0;
        for(Document document : documents){
            document.setZeroBasedId(zeroBasedId);
            zeroBasedId++;
        }
    }

    /**
     * Compute DBCA.
     */
    @Override
    public void processCoOccurrenceCounting() {
        double[][] bcaMatrix = this.transform(this.retainedElements);
        int[] nodes = UnitOfAnalysis.getZeroBasedId(this.retainedElements);
        this.coOccurrenceMatrix = new CoOccurrenceMatrix(nodes, bcaMatrix);
        super.setSelectedSubsetMetrics();
    }

    /**
     * Transform a documents-references data structure to a documents-documents matrix.
     * Calculate bibliographic coupling indices and fill the matrix.
     * @param documents Documents retained in the analysis.
     */
    private double[][] transform(List<Document> documents) {
        int nNodes = documents.size();
        double[][] bcaMatrix = new double[nNodes][nNodes];

        for(int i = 0; i < bcaMatrix.length; i++){ // Line
            for(int j=0; j < bcaMatrix.length; j++){ // Column
                if(i==j){
                    bcaMatrix[i][j] = 0; // Matrix diagonal
                }else if (i > j) { // The matrix is squared, only need to populate one the middle part of the matrix, the other part is a copy
                    Document lineDocument = documents.get(i);
                    Document columnDocument = documents.get(j);
                    bcaMatrix[i][j] = lineDocument.computeBibliographicCoupling(columnDocument);
                    bcaMatrix[j][i] = bcaMatrix[i][j]; // Populate the opposite case, symmetric
                }
            }
        }

        return bcaMatrix;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\nBCA analysis");
        builder.append(super.toString());
        return builder.toString();
    }

}
