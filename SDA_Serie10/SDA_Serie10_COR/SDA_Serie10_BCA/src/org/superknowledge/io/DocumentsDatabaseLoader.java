package org.superknowledge.io;

import org.superknowledge.business.Documents;

/**
 * Parent class of documents database loader.
 */
public abstract class DocumentsDatabaseLoader {

    /**
     * Filepath of the documents database file.
     */
    protected String filepath;

    /**
     * Documents loaded.
     */
    protected Documents documents;

    public DocumentsDatabaseLoader(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

}
