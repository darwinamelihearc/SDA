import org.superknowledge.business.Document;
import org.superknowledge.io.DocumentsCsvDatabaseLoader;
import org.superknowledge.services.cooccurrence.DBCAProcessor;
import org.superknowledge.services.cooccurrence.CoOccurrenceProcessor;


public class Main {

    public static void main(String[] args) {
        computeBCA();
    }

    private static void computeBCA() {
        String filepath = "data/bibliometric_top20_scopus_export.csv";
        // Charge les données en mémoire
        DocumentsCsvDatabaseLoader loader = new DocumentsCsvDatabaseLoader(filepath);
        loader.process();

        System.out.println(loader.getDocuments().toStringSynthesis());

        // Effectue les calculs de couplage bibliographique
        CoOccurrenceProcessor<Document> processor = new DBCAProcessor(
                loader.getDocuments(),
                0);
        processor.processAll();

        System.out.println(processor.toString());

    }

}