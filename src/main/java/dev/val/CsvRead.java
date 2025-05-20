package dev.val;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvRead {

    public static record CsvRow(String direction, String Year, String date, String weekday, String country,
            String commodity, String transport_mode, String measure, String value) {
    }

    public static List<String> parseQuotedLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();

        for (char c : line.toCharArray()) {
            switch (c) {
                case '"':
                    inQuotes = !inQuotes;
                    break;
                case ',':
                    if (!inQuotes) {
                        fields.add(currentField.toString().trim());
                        currentField = new StringBuilder();
                    } else {
                        currentField.append(c);
                    }
                    break;
                default:
                    currentField.append(c);
            }
        }
        fields.add(currentField.toString().trim());
        return fields;
    }

    public static List<CsvRead.CsvRow> readFile(String path) {
        List<CsvRead.CsvRow> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                List<String> row = parseQuotedLine(line);
                data.add(new CsvRow(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6),
                        row.get(7), row.get(8)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("We cannot locate this file...");
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        return data;
    }
}