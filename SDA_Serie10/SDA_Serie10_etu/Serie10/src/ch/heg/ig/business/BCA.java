package ch.heg.ig.business;

import ch.heg.ig.datastructure.Document;
import ch.heg.ig.datastructure.Reference;

import java.util.List;

public class BCA {
    int score = 0;
    private int[][] couplingMatrix;

    public BCA(int numberOfDocuments) {
        this.couplingMatrix = new int[numberOfDocuments][numberOfDocuments];
    }

    public void calculateCoupling(List<Document> documents) {
        for (int i = 0; i < documents.size(); i++) {
            for (int j = i + 1; j < documents.size(); j++) {
                score = calculateScore(documents.get(i), documents.get(j));
                couplingMatrix[i][j] = score;
                couplingMatrix[j][i] = score; // La matrice est symétrique
            }
        }
    }

    private int calculateScore(Document doc1, Document doc2) {
        score = 0;
        List<Reference> referencesDoc1 = doc1.getReferences();
        List<Reference> referencesDoc2 = doc2.getReferences();

        for (Reference ref1 : referencesDoc1) {
            for (Reference ref2 : referencesDoc2) {
                if (ref1.equals(ref2)) {
                    score++;
                }
            }
        }
        return score;
    }
    public BCA(int[][] couplingMatrix) {
        this.couplingMatrix = couplingMatrix;
    }

    public int[][] getCouplingMatrix() {
        return couplingMatrix;
    }

    public void setCouplingMatrix(int[][] couplingMatrix) {
        this.couplingMatrix = couplingMatrix;
    }
}
