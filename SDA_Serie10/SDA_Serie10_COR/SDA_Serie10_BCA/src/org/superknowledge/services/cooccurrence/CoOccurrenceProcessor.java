package org.superknowledge.services.cooccurrence;

import java.util.*;

import org.superknowledge.business.Documents;
import org.superknowledge.business.Document;
import org.superknowledge.business.UnitOfAnalysis;
import org.superknowledge.business.Reference;
import org.superknowledge.business.CoOccurrenceMatrix;


/**
 * Parent class of a co-occurrence analysis.
 * Two types of co-occurrence analysis can be processed: CCA or BCA.
 * The {@link UnitOfAnalysis} is a document in BCA and a reference in CCA.
 * @author Maximiliano Jeanneret Medina
 */
public abstract class CoOccurrenceProcessor<T extends UnitOfAnalysis> implements BibProcessor {

    /**
     * Documents database.
     * Input field.
     */
    protected final Documents documents;

    /**
     * The documents above this threshold will be retained.
     * The citation threshold can be normalised.
     */
    private final double citationThreshold;

    /**
     * Documents or references retained in the analysis.
     * {@link UnitOfAnalysis} can be ranked by threshold.
     */
    protected List<T> retainedElements;

    /**
     * Co-citation references or bibliographic coupling matrix.
     * This matrix is squared.
     */
    protected CoOccurrenceMatrix coOccurrenceMatrix;

    public CoOccurrenceProcessor(Documents documents, double citationThreshold) {
        this.documents = documents;
        this.citationThreshold = citationThreshold;
        this.retainedElements = null;
        this.coOccurrenceMatrix = null;
    }

    /**
     * Template method to perform these operations:
     * Filter operation by retaining a subset of elements above a threshold
     * Sort retained elements by name
     * Process the co-occurrence analysis with the retained elements
     * At each operation the state of this object instance change. Intermediary results are stored in memory and can be written on files or database after the processing.
     */
    public void processAll() {
        this.retainedElements = filter(this.citationThreshold);
        checkSelectedElementsValidity();
        sortByName(this.retainedElements);
        processCoOccurrenceCounting();
    }

    protected abstract List<T> filter(double citationThreshold);

    protected abstract void sortByName(List<T> selectedUnitOfAnalysis);

    /**
     * Verify the unicity of Document or Reference retained depending the analysis.
     * UnitOfAnalysis, Document and Reference equals and hashcode need to be overridden.
     */
    private void checkSelectedElementsValidity(){

        String type = "";
        Set<UnitOfAnalysis> uniqueRetainedElements = new HashSet<>();
        for(UnitOfAnalysis unitOfAnalysis : this.retainedElements){
            if(unitOfAnalysis instanceof Document) {
                type = "document";
            }else if(unitOfAnalysis instanceof Reference){
                type = "reference";
            }

            if(!uniqueRetainedElements.add(unitOfAnalysis))
                throw new IllegalStateException("Duplicated " + type + " in the co-occurrence analysis result. Please relaunch the process from the beginning.");

            if(!unitOfAnalysis.isRetainedInAnalyis())
                throw new IllegalStateException("A " + type + " is not allowed to be retained in the analysis");
        }
    }

    protected void setSelectedSubsetMetrics(){
        int[] neighborsPerNode = this.coOccurrenceMatrix.getNbNeighborsPerNode();
        double[] totalEdgeWeightPerNode = this.coOccurrenceMatrix.getTotalEdgeWeightPerNode();

        for(int i = 0; i < this.retainedElements.size(); i++){
            this.retainedElements.get(i).getBibliometricMetric().setNbConnectedNodes(neighborsPerNode[i]);
            this.retainedElements.get(i).getBibliometricMetric().setTotalEdgeWeight(totalEdgeWeightPerNode[i]);
        }
    }

    public Documents getDocuments() {
        return documents;
    }

    public double getCitationThreshold() {
        return citationThreshold;
    }

    public CoOccurrenceMatrix getCoOccurrenceMatrix() {
        return coOccurrenceMatrix;
    }

    public List<T> getRetainedElements() {
        return retainedElements;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nRetained elements:\n");
        stringBuilder.append(this.getRetainedElements());
        stringBuilder.append("\nMatrix:\n");
        stringBuilder.append(this.getCoOccurrenceMatrix().toStringMatrix(true, true));
        stringBuilder.append("\nList:\n");
        stringBuilder.append(this.getCoOccurrenceMatrix().toStringIntegerNetwork());
        return stringBuilder.toString();
    }

}
