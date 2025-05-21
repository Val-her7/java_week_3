package dev.val;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Arrays;

public class CsvReadTest {
    @Test
    void parseNoQuotedLineTest(){
        List<String> lineToList = Arrays.asList("Exports", "2015", "01/01/2015", "Thursday", "All", "All", "All", "$", "104000000", "104000000");
        assertEquals(lineToList, CsvRead.parseQuotedLine("Exports,2015,01/01/2015,Thursday,All,All,All,$,104000000,104000000"));
    }
    @Test
    void parseQuotedLineTest(){
        List<String> lineToList = Arrays.asList("Exports", "2017", "12/04/2017", "Wednesday", "China", "Milk powder, butter, and cheese", "All", "Tonnes", "0", "223000");
        assertEquals(lineToList, CsvRead.parseQuotedLine("Exports,2017,12/04/2017,Wednesday,China,\"Milk powder, butter, and cheese\",All,Tonnes,0,223000"));
    }
    @Test
    void readFileExistTest(){
        String filePath = "src\\main\\Ressources\\covid_and_trade.csv";
        List<CsvRead.CsvRow> data = CsvRead.readFile(filePath);
        assertEquals(104973, data.size());

        CsvRead.CsvRow firstLine = data.get(0);
        assertEquals("Exports", firstLine.direction());
        assertEquals("2015", firstLine.Year());
        assertEquals("01", firstLine.month());
        assertEquals("Thursday", firstLine.weekday());
        assertEquals("All", firstLine.country());
        assertEquals("All", firstLine.commodity());
        assertEquals("All", firstLine.transport_mode());
        assertEquals("$", firstLine.measure());
        assertEquals("104000000", firstLine.value());
    }
    @Test
    void readlFileNotExistTest(){
        String filePath = "src\\main\\Ressources\\wrong_name.csv";
        List<CsvRead.CsvRow> data = CsvRead.readFile(filePath);
        assertEquals(0, data.size() );
    }
}