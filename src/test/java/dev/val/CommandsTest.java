package dev.val;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @Test
    void getYearlyTotalTest() throws Exception {
        Path tempFile = Files.createTempFile("covid_and_trade_test", ".csv");
        List<String> lines = List.of(
            "Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative",
            "Exports,2015,01/01/2015,Thursday,All,All,All,$,100000000,100000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,$,200000000,300000000",
            "Exports,2015,01/02/2015,Monday,All,All,All,$,100000000,400000000",
            "Imports,2015,01/02/2015,Thursday,All,All,All,$,100000000,500000000",
            "Imports,2015,01/05/2015,Thursday,All,All,All,$,500000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,$,300000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,$,100000000,500000000",
            "Reimports,2015,01/11/2015,Thursday,All,All,All,$,100000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,Tonnes,100000000,500000000"
        );
        Files.write(tempFile, lines);

        List<CsvRead.CsvRow> records = CsvRead.readFile(tempFile.toString());
        Commands command = new Commands(records);

        Map<String, Long> expectedMap = new LinkedHashMap<>();
        expectedMap.put("01", 300000000L);
        expectedMap.put("02", 200000000L);
        expectedMap.put("05", 500000000L);
        expectedMap.put("11", 400000000L);

        assertEquals(expectedMap, command.getYearlyTotal("2015",  "All", "All", "All", "$"));
    }
    @Test
    void getYearlyAverageTest() throws Exception {
        Path tempFile = Files.createTempFile("covid_and_trade_test", ".csv");
        List<String> lines = List.of(
            "Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative",
            "Exports,2015,01/01/2015,Thursday,All,All,All,$,100000000,100000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,$,200000000,300000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,$,200000000,300000000",
            "Exports,2015,01/02/2015,Monday,All,All,All,$,100000000,400000000",
            "Imports,2015,01/02/2015,Thursday,All,All,All,$,100000000,500000000",
            "Imports,2015,01/05/2015,Thursday,All,All,All,$,500000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,$,300000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,$,100000000,500000000",
            "Reimports,2015,01/11/2015,Thursday,All,All,All,$,100000000,500000000",
            "Imports,2015,01/11/2015,Thursday,All,All,All,Tonnes,100000000,500000000"
        );
        Files.write(tempFile, lines);

        List<CsvRead.CsvRow> records = CsvRead.readFile(tempFile.toString());
        Commands command = new Commands(records);

        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put("01", (100000000.0 + 200000000.0 + 200000000.0) / 3);
        expectedMap.put("02", 100000000.0);
        expectedMap.put("05", 500000000.0);
        expectedMap.put("11", 200000000.0);

        assertEquals(expectedMap, command.getYearlyAverage("2015",  "All", "All", "All", "$"));
    }
    @Test
    void getOverviewTest() throws Exception{
        Path tempFile = Files.createTempFile("covid_and_trade_test", ".csv");
        List<String> lines = List.of(
            "Direction,Year,Date,Weekday,Country,Commodity,Transport_Mode,Measure,Value,Cumulative",
            "Exports,2015,01/01/2015,Thursday,All,All,Air,$,100000000,100000000",
            "Imports,2015,01/01/2015,Thursday,All,All,All,Tonnes,200000000,300000000",
            "Exports,2016,01/02/2016,Monday,China,All,All,$,100000000,400000000",
            "Reimports,2017,01/01/2015,Thursday,All,\"Milk powder, butter, and cheese\",All,$,100000000,500000000"
        );
        Files.write(tempFile, lines);

        List<CsvRead.CsvRow> records = CsvRead.readFile(tempFile.toString());
        Commands command = new Commands(records);

        assertEquals(Set.of("2015", "2016", "2017"), command.getOverview("Year"));
        assertEquals(Set.of("All", "China"), command.getOverview("Country"));
        assertEquals(Set.of("All", "Milk powder, butter, and cheese"), command.getOverview("Commodity"));
        assertEquals(Set.of("All", "Air"), command.getOverview("Transport_Mode"));
        assertEquals(Set.of("$", "Tonnes"), command.getOverview("Measure"));
    }
}