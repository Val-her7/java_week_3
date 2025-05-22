package dev.val;

import java.util.Scanner;

public class CommandsHandler {
    public Commands command;

    public CommandsHandler(Commands command){
        this.command = command;
    }

    public void execute(Scanner scanner, String input){
        String[] inputCommand = input.trim().split(" ");
        String instruction = inputCommand[0];

        switch(instruction){
            case "HELP" -> {
                if(inputCommand.length == 1){
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

                System.out.print("Year: ");
                String year = scanner.nextLine();

                System.out.print("Month: ");
                String month = scanner.nextLine();

                System.out.print("Country (default All): ");
                String countryInput = scanner.nextLine();
                String country = countryInput.isEmpty() ? "All" : countryInput;

                System.out.print("Commodity (default All): ");
                String commodityInput = scanner.nextLine();
                String commodity = commodityInput.isEmpty() ? "All" : commodityInput;

                System.out.print("Transport_Mode (default All): ");
                String transportModeInput = scanner.nextLine();
                String transportMode = transportModeInput.isEmpty() ? "All" : transportModeInput;

                System.out.println("Measure (default $): ");
                String measureInput = scanner.nextLine();
                String measure = measureInput.isEmpty() ? "$" : measureInput;

                System.out.println(command.getMonthlyTotal(year, month, country, commodity, transportMode, measure));
            }
            default -> System.out.println("Invalid command");
        }
    }
}