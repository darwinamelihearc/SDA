package org.superknowledge.business;

/**
 * Co-occurrence attributes
 */
public class BibliometricMetric {

    /**
     * Also seen as "(raw) citation weight".
     */
    private int rawCitationCount;

    /**
     * Also seen as "normalised citation weight".
     */
    private double normalisedCitationCount;

    /**
     * Also seen as "links" or "nb links".
     */
    private int nbConnectedNodes;

    /**
     * Also seen as "Total link strength".
     */
    private double totalEdgeWeight;

    /**
     * Citation score.
     * In CCA (precisely RCCA) this attribute refers to the number of references co-citation.
     * In BCA precisely (DBCA), this attribute refers to the number of documents bibliographic coupling.
     * @implNote This value can be normalised or not. This value is calculated in Matrices module.
     */

    public BibliometricMetric() {
        rawCitationCount = 0;
        normalisedCitationCount = 0;
        nbConnectedNodes = 0;
        totalEdgeWeight = 0;
    }

    public int getRawCitationCount() {
        return rawCitationCount;
    }

    public void setRawCitationCount(int rawCitationCount) {
        checkRawCitationCount(rawCitationCount);
        this.rawCitationCount = rawCitationCount;
    }

    private void checkRawCitationCount(int rawCitationCount) throws IllegalArgumentException {
        if (rawCitationCount < 0)
            throw new IllegalStateException("Raw citation count must be >= 0. Current value: " + rawCitationCount);
    }

    public double getNormalisedCitationCount() {
        return normalisedCitationCount;
    }

    public void setNormalisedCitationCount(double normalisedCitationCount) {
        this.normalisedCitationCount = normalisedCitationCount;
    }

    public int getNbConnectedNodes() {
        return nbConnectedNodes;
    }

    public void setNbConnectedNodes(int nbConnectedNodes) {
        this.nbConnectedNodes = nbConnectedNodes;
    }

    public double getTotalEdgeWeight() {
        return totalEdgeWeight;
    }

    public void setTotalEdgeWeight(double totalEdgeWeight) {
        this.totalEdgeWeight = totalEdgeWeight;
    }

}
