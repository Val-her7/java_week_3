package dev.val;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<CsvRead.CsvRow> records = CsvRead.readFile("src\\main\\Ressources\\covid_and_trade.csv");

        Commands command = new Commands(records);
        CommandsHandler processor = new CommandsHandler(command);

        System.out.println("Enter help to enter the programm!");

        while(true){
            System.out.print("> ");
            String input = scanner.nextLine().toUpperCase();
            if(input.equals("QUIT"))break;
            processor.execute(scanner, input);
        }
        scanner.close();
    }
}