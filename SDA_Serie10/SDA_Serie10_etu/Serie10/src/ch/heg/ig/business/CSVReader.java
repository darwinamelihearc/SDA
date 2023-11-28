package ch.heg.ig.business;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class CSVReader<T> {

    public List<T> readCSV(String filePath) {
        List<T> items = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                T item = parseLine(nextLine);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    protected abstract T parseLine(String[] line);
}
