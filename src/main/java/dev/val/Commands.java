package dev.val;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;

import dev.val.CsvRead.CsvRow;

public class Commands {
    public List<CsvRead.CsvRow> records;

    public Commands(List<CsvRead.CsvRow> records) {
        this.records = records;
    }

    public Long getMonthlyTotal(String year, String month, String country, String commodity, String transport_mode,
            String measure) {
        return records.stream()
                .filter(r -> (r.direction().equals("Imports")
                        || r.direction().equals("Exports"))
                        && (r.Year().equals(year) && r.month().equals(month))
                        && r.country().equals(country)
                        && r.commodity().equals(commodity)
                        && r.transport_mode().equals(transport_mode)
                        && r.measure().equals(measure))
                .mapToLong(r -> Long.parseLong(r.value()))
                .sum();
    }

    public Double getMonthlyAverage(String year, String month, String country, String commodity, String transport_mode,
            String measure) {
        return records.stream()
                .filter(r -> (r.direction().equals("Imports")
                        || r.direction().equals("Exports"))
                        && (r.Year().equals(year) && r.month().equals(month))
                        && r.country().equals(country)
                        && r.commodity().equals(commodity)
                        && r.transport_mode().equals(transport_mode)
                        && r.measure().equals(measure))
                .mapToDouble(r -> Double.parseDouble(r.value()))
                .average()
                .orElse(0);
    }

    public Map<String, Long> getYearlyTotal(String year, String country, String commodity, String transport_mode,
            String measure) {
        return records.stream()
                .filter(r -> (r.direction().equals("Imports")
                        || r.direction().equals("Exports"))
                        && r.Year().equals(year)
                        && r.country().equals(country)
                        && r.commodity().equals(commodity)
                        && r.transport_mode().equals(transport_mode)
                        && r.measure().equals(measure))
                .collect(Collectors.groupingBy(CsvRow::month, LinkedHashMap::new,
                        Collectors.summingLong(c -> Long.parseLong(c.value()))));
    }

    public Map<String, Double> getYearlyAverage(String year, String country, String commodity, String transport_mode,
            String measure) {
        return records.stream()
                .filter(r -> (r.direction().equals("Imports")
                        || r.direction().equals("Exports"))
                        && r.Year().equals(year)
                        && r.country().equals(country)
                        && r.commodity().equals(commodity)
                        && r.transport_mode().equals(transport_mode)
                        && r.measure().equals(measure))
                .collect(Collectors.groupingBy(CsvRow::month, LinkedHashMap::new,
                        Collectors.averagingDouble(c -> Double.parseDouble(c.value()))));
    }

    public Set<String> getOverview(String columnName){
        Set<String> uniqueValues = new TreeSet<>();
        for(CsvRow row: records){
            switch(columnName){
                case "Year" -> uniqueValues.add(row.Year());
                case "Country" -> uniqueValues.add(row.country());
                case "Commodity" -> uniqueValues.add(row.commodity());
                case "Transport_Mode" -> uniqueValues.add(row.transport_mode());
                case "Measure" -> uniqueValues.add(row.measure());
            }
        }
        return uniqueValues;
    }

    public void showCommands(){
        System.out.println("""
                        help: returns a list of available commands with a small description.
                        help <command>: returns a full explanation of what the command does and what parameters it needs.
                        monthly_total: returns the sum of both the export and import for a specified month of a specified year.
                        monthly_average: returns the average of both the export and the import of a specified month of a specified year.
                        yearly_total: returns an overview of all the monthly totals for a particular year. This command returns the total of each month for both import and export. Then it gives the yearly total for both import and export.
                        yearly_average: returns an overview of all the montly averages for a particular year, for both import and export. Then it gives the yearly average for both import and export.
                        overview: returns all the unique values that span the data set: years, countries, commodities, transportation modes and measures.
                        """);
    }
}