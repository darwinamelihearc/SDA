package org.superknowledge.io;

public enum DocumentReferenceConstant {

    TITLE ( "Title"),
    AUTHORS ( "Authors"),
    ABSTRACT ( "Abstract"),
    YEAR ( "Year"),
    DOI ( "DOI"),
    SOURCE_TITLE ( "Source title"),
    VOLUME ( "Volume"),
    ISSUE ( "Issue"),
    PAGE_START ( "Page start"),
    PAGE_END ( "Page end"),
    AUTHOR_KEYWORDS ( "Author Keywords"),
    INDEX_KEYWORDS ( "Index Keywords"),
    CITED_BY ( "Cited by"),
    REFERENCES ( "References"),
    SCOPUS_LINK ( "Scopus link"),
    SCOPUS_CITED_BY_LINK ( "Scopus cited-by");

    private int columnIndex;
    private final String columnName;

    DocumentReferenceConstant(String columnName) {
        this.columnIndex = -1;
        this.columnName = columnName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getColumnName() {
        return columnName;
    }

}
