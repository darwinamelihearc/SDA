package ch.heg.ig.business;

import ch.heg.ig.datastructure.Document;
import ch.heg.ig.datastructure.Reference;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentCSVReader extends CSVReader<Document> {

    // Authors,Author(s) ID,Title,Year,Source title,Volume,Issue,Art. No.,Page start,Page end,Page count,Cited by,DOI,Link,Abstract,Author Keywords,Index Keywords,References,Editors,Sponsors,Publisher,Conference name,Conference date,Conference location,Conference code,Language of Original Document,Abbreviated Source Title,Document Type,Publication Stage,Open Access,Source,EID
    @Override
    protected Document parseLine(String[] line) {
        String title = line[2];
        List<String> authors = Arrays.asList(line[0].split(", "));
        int year = Integer.parseInt(line[3]);
        List<Reference> references = parseReferences(line[17]);

        return new Document(title, authors, year, references);
    }

    private List<Reference> parseReferences(String referencesString) {
        List<Reference> referencesList = new ArrayList<>();

        referencesList.
        // Logique pour parser les références
        return referencesList;
    }
}
