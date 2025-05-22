package dev.val;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandsHandler {
    public Commands command;

    public CommandsHandler(Commands command) {
        this.command = command;
    }

    public static Map<String, String> askParameters(Scanner scanner, boolean monthNeeded) {
        Map<String, String> params = new HashMap<>();

        System.out.print("Year: ");
        params.put("year", scanner.nextLine());

        if (monthNeeded) {
            System.out.print("Month(01, 02, 03, ...): ");
            params.put("month", scanner.nextLine());
        }

        System.out.print("Country (default All): ");
        String country = scanner.nextLine();
        params.put("country", country.isEmpty() ? "All" : country);

        System.out.print("Commodity (default All): ");
        String commodity = scanner.nextLine();
        params.put("commodity", commodity.isEmpty() ? "All" : commodity);

        System.out.print("Transport_Mode (default All): ");
        String transport = scanner.nextLine();
        params.put("transport", transport.isEmpty() ? "All" : transport);

        System.out.print("Measure (default $): ");
        String measure = scanner.nextLine();
        params.put("measure", measure.isEmpty() ? "$" : measure);

        return params;
    }

    public void execute(Scanner scanner, String input) {
        String[] inputCommand = input.trim().split(" ");
        String instruction = inputCommand[0];

        switch (instruction) {
            case "HELP" -> {
                if (inputCommand.length == 1) {
                    command.showCommands();
                } else {
                    command.showCommands(inputCommand[1].replaceAll("[<>]", ""));
                }
            }
            case "OVERVIEW" -> {
                System.out.println("Years: " + command.getOverview("Year"));
                System.out.println("Countries: " + command.getOverview("Country"));
                System.out.println("Commodities: " + command.getOverview("Commodity"));
                System.out.println("Transport_Modes: " + command.getOverview("Transport_Mode"));
                System.out.println("Measures: " + command.getOverview("Measure"));
            }
            case "MONTHLY_TOTAL" -> {
                Map<String, String> params = askParameters(scanner, true);
                System.out.println(" The sum of both the export and import for " + params.get("month") + "/"
                        + params.get("year") + " = " + command.getMonthlyTotal(
                                params.get("year"),
                                params.get("month"),
                                params.get("country"),
                                params.get("commodity"),
                                params.get("transport"),
                                params.get("measure"))
                        + " " + params.get("measure"));
            }
            case "MONTHLY_AVERAGE" -> {
                Map<String, String> params = askParameters(scanner, true);
                System.out.println(" The average of both the export and import for " + params.get("month") + "/"
                        + params.get("year") + " = " + command.getMonthlyAverage(
                                params.get("year"),
                                params.get("month"),
                                params.get("country"),
                                params.get("commodity"),
                                params.get("transport"),
                                params.get("measure"))
                        + " " + params.get("measure"));
            }
            case "YEARLY_TOTAL" -> {
                Map<String, String> params = askParameters(scanner, false);
                Map<String, Long> yearly_tot = command.getYearlyTotal(
                        params.get("year"),
                        params.get("country"),
                        params.get("commodity"),
                        params.get("transport"),
                        params.get("measure"));
                System.out.println("--------------------------------");
                System.out.println("Months | Sum(Imports + Exports)");
                System.out.println("--------------------------------");
                for (String month : yearly_tot.keySet()) {
                    System.out.println(month + " | " + yearly_tot.get(month));
                }
                Long sumOfMonths = yearly_tot.values().stream().mapToLong(d -> d.longValue()).sum();
                System.out.println("Yearly_Total | " + sumOfMonths);
            }
            case "YEARLY_AVERAGE" -> {
                Map<String, String> params = askParameters(scanner, false);
                Map<String, Double> yearly_avg = command.getYearlyAverage(
                        params.get("year"),
                        params.get("country"),
                        params.get("commodity"),
                        params.get("transport"),
                        params.get("measure"));
                System.out.println("--------------------------------");
                System.out.println("Months | Average(Imports + Exports)");
                System.out.println("--------------------------------");
                for (String month : yearly_avg.keySet()) {
                    System.out.println(month + " | " + yearly_avg.get(month));
                }
                Double avgOfMonths = yearly_avg.values().stream().mapToDouble(d -> d.doubleValue()).average().orElse(0);
                System.out.println("Yearly_Total | " + avgOfMonths);
            }
            default -> System.out.println("Invalid command");
        }
    }
}