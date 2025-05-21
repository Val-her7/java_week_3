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
}