package dev.val;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandsTest {
    @Test
    void getMonthlyTotalTest() throws Exception {
        Path tempFile = Files.createTempFile("covid_and_trade_test", ".csv");
        List<String> lines = List.of(
            "Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative",
            "Exports,2015,01/01/2015,Thursday,All,All,All,$,100000000,100000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,$,200000000,300000000",
            "Exports,2016,01/02/2016,Monday,All,All,All,$,100000000,400000000",
            "Reimports,2015,01/01/2015,Thursday,All,All,All,$,100000000,500000000"
        );
        Files.write(tempFile, lines);

        List<CsvRead.CsvRow> records = CsvRead.readFile(tempFile.toString());
        Commands command = new Commands(records);

        assertEquals(300000000, command.getMonthlyTotal("2015", "01", "All", "All", "All", "$"));
        assertEquals(0, command.getMonthlyTotal("2015", "02", "All", "All", "All", "$"));
        assertEquals(100000000, command.getMonthlyTotal("2016", "02", "All", "All", "All", "$"));
    }
    @Test
    void getMonthlyAverageTest() throws Exception {
        Path tempFile = Files.createTempFile("covid_and_trade_test", ".csv");
        List<String> lines = List.of(
            "Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative",
            "Exports,2015,01/01/2015,Thursday,All,All,All,$,100000000,100000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,$,200000000,300000000",
            "Exports,2016,01/02/2016,Monday,All,All,All,$,100000000,400000000",
            "Reimports,2015,01/01/2015,Thursday,All,All,All,$,100000000,500000000"
        );
        Files.write(tempFile, lines);

        List<CsvRead.CsvRow> records = CsvRead.readFile(tempFile.toString());
        Commands command = new Commands(records);

        assertEquals(150000000, command.getMonthlyAverage("2015", "01", "All", "All", "All", "$"));
        assertEquals(0, command.getMonthlyAverage("2015", "02", "All", "All", "All", "$"));
        assertEquals(100000000, command.getMonthlyAverage("2016", "02", "All", "All", "All", "$"));
        assertEquals(0, command.getMonthlyAverage("2015", "01", "China", "All", "All", "$"));
    }
}
