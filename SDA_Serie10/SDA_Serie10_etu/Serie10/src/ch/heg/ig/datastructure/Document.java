package ch.heg.ig.datastructure;

import java.util.List;
import java.util.Objects;

public class Document {
    private String title;
    private List<String> authors;
    private int year;
    private List<Reference> references;

    public Document(String title, List<String> authors, int year, List<Reference> references) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.references = references;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return getYear() == document.getYear() && Objects.equals(getTitle(), document.getTitle()) && Objects.equals(getAuthors(), document.getAuthors()) && Objects.equals(getReferences(), document.getReferences());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthors(), getYear(), getReferences());
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", year=" + year +
                ", references=" + references +
                '}';
    }
}
