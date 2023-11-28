package ch.heg.ig.datastructure;

import java.util.List;
import java.util.Objects;

public class Reference {
    private String title;
    private List<String> authors;
    private int year;

    public Reference(String title, List<String> authors, int year) {
        this.title = title;
        this.authors = authors;
        this.year = year;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference reference)) return false;
        return getYear() == reference.getYear() && Objects.equals(getTitle(), reference.getTitle()) && Objects.equals(getAuthors(), reference.getAuthors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthors(), getYear());
    }

    @Override
    public String toString() {
        return "Reference{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", year=" + year +
                '}';
    }
}
