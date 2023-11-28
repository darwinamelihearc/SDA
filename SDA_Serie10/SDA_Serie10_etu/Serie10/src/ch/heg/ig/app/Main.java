package ch.heg.ig.app;

import ch.heg.ig.business.BCA;
import ch.heg.ig.business.CSVReader;
import ch.heg.ig.business.DocumentCSVReader;
import ch.heg.ig.datastructure.Document;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String filePath = "../data/bibliometric_top20_scopus_export.csv";

        CSVReader<Document> csvReader = new DocumentCSVReader();
        List<Document> documents = csvReader.readCSV(filePath);

        BCA bca = new BCA(documents.size());
        bca.calculateCoupling(documents);
    }
}