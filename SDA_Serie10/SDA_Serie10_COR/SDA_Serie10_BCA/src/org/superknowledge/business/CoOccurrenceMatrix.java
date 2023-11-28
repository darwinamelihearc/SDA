package org.superknowledge.business;

import java.text.DecimalFormat;

public class CoOccurrenceMatrix {

    /**
     * Nodes id.
     * Each id need id unique.
     */
    private final int[] nodes;

    /**
     * Matrix that contains co-citations or bibliographic coupling weights.
     */
    private final double[][] weights;

    public CoOccurrenceMatrix(int[] nodes, double[][] weights){
        this.nodes = nodes;
        this.weights = weights;
    }

    /**
     * Get the number of neighbors for each node.
     * CCA : Number of references linked
     * BCA : Number of docs connected
     */
    public int[] getNbNeighborsPerNode(){

        int[] neighborsPerNode = new int[this.weights.length];

        int i, j;
        for (i = 0; i < this.weights.length; i++) {
            int nbConnectedNodes = 0;
            for (j = 0; j < this.weights.length; j++) {
                if(i != j) {
                    nbConnectedNodes += (this.weights[i][j] > 0 ? 1 : 0);
                }
            }

            neighborsPerNode[i] = nbConnectedNodes;
        }

        return neighborsPerNode;
    }

    /**
     * Get the sum of links for each node.
     * CCA : Total number of links
     * BCA : Total number of connections
     */
    public double[] getTotalEdgeWeightPerNode()
    {
        double[] totalEdgeWeightPerNode = new double[this.nodes.length];

        for (int i = 0; i < this.weights.length; i++) {
            double totalEdgeWeight = 0;
            for (int j = 0; j < this.weights.length; j++) {
                if(i != j) {
                    totalEdgeWeight += this.weights[i][j];
                }
            }

            totalEdgeWeightPerNode[i] = totalEdgeWeight;
        }

        return totalEdgeWeightPerNode;
    }

    public int[] getNodes() {
        return nodes;
    }

    public double[][] getWeights() {
        return weights;
    }

    // String

    /**
     * Create a string containing {@code weights} in integer.
     * @param withNodesId If true, the first line and the first column of the matrix will contains the nodes id.
     * @return Co-occurrence matrix in TSV format.
     */
    public String toStringMatrix(boolean withNodesId, boolean isInteger) {
        DecimalFormat df = new DecimalFormat("#####.###");
        StringBuilder stringBuilder = new StringBuilder();
        int i, j;

        // First line : nodes id
        if(withNodesId){
            stringBuilder.append("\t");
            for(i = 0; i < this.nodes.length; i++){
                stringBuilder.append(this.nodes[i]);
                if(i < this.nodes.length-1)
                    stringBuilder.append("\t");
            }
            stringBuilder.append("\n");
        }

        for (i = 0; i < this.weights.length; i++) {
            // Column node id
            if(withNodesId) {
                stringBuilder.append(this.nodes[i]);
                stringBuilder.append("\t");
            }

            for (j = 0; j < this.weights.length; j++) {
                if(isInteger) {
                    stringBuilder.append(Math.round(this.weights[i][j]));
                }else{
                    stringBuilder.append(df.format((this.weights[i][j])));
                }
                if(j < this.weights.length-1)
                    stringBuilder.append("\t");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Create the network with integer weights.
     * Only the middle part of the matrix is converted in the network to not have duplicated relations
     * @return Co-occurrence network in TSV format.
     */
    public String toStringIntegerNetwork() {
        StringBuilder stringBuilder = new StringBuilder();

        int i, j;
        for (i = 0; i < this.weights.length; i++) {
            for (j = 0; j < this.weights.length; j++) {
                if(j > i){
                    stringBuilder.append(this.nodes[i]);
                    stringBuilder.append("\t");
                    stringBuilder.append(this.nodes[j]);
                    stringBuilder.append("\t");
                    stringBuilder.append(Math.round(this.weights[i][j]));
                    stringBuilder.append("\n");
                }
            }
        }

        return stringBuilder.toString();
    }

}
