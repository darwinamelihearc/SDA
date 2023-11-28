package org.superknowledge.business;

import java.util.*;

/**
 * Documents (and references) of a bibliometric analysis.
 */
public class Documents {

    /**
     * Documents database.
     * First order sample.
     * The references contained in documents are the same that the references stored in uniqueReferences
     */
    private final List<Document> documents;

    /**
     * Total number of references.
     */
    private int nbReferences;

    /**
     * Unique references.
     * A treemap is used to attribute the first id to the first reference kez (alphanumeric sorting)
     */
    private final SortedMap<String, Reference> uniqueReferences;

    /**
     * A treemap is used to attribute the first id to the first reference key (alphanumeric sorting)
     * @param size
     */
    public Documents(int size){
        this.documents = new ArrayList<>(size);
        this.uniqueReferences = new TreeMap<>();
    }

    /**
     * Add a document and unique references.
     * The object references stored in documents and unique references are the same.
     * @param document
     */
    public void addDocumentAndUniqueReferences(Document document){
        documents.add(document);

        nbReferences += document.getReferences().size();

        // Unique references
        for (Map.Entry<String, Reference> entry : document.getReferences()) {
            addUniqueReference(entry.getValue());
        }
    }

    /**
     * Add or update unique reference.
     * The unicity is defined by a unique string.
     * @param reference
     * @return
     */
    private Reference addUniqueReference(Reference reference){
        return uniqueReferences.put(reference.getReferenceKey(), reference);
    }

    /**
     * Set unique id and update the document's references.
     * The id is updated in both data structure because the object reference is the same.
     */
    public void setReferencesUniqueId(){
        int uniqueZeroBasedId = 0;
        for (Map.Entry<String, Reference> entry : uniqueReferences.entrySet()) {
            entry.getValue().setZeroBasedId(uniqueZeroBasedId);
            uniqueZeroBasedId++;
        }
    }

    public int getNbDocuments(){
        return this.documents.size();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public int getNbReferences(){
        return this.nbReferences;
    }

    public SortedMap<String, Reference> getUniqueReferences() {
        return uniqueReferences;
    }

    public int getNbUniqueReferences(){
        return this.uniqueReferences.size();
    }

    public String toStringSynthesis(){
        StringBuilder stb = new StringBuilder();
        stb.append("Documents and references synthesis: ");
        stb.append("\n\tNumber of documents: ").append(this.getNbDocuments());
        stb.append("\n\tNumber of references: ").append(this.getNbReferences());
        stb.append("\n\tNumber of unique references: ").append(this.getNbUniqueReferences());
        return stb.toString();
    }

}
