import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GraderTest {
    @Test
    void fiftynineShouldReturnS(){
        Grader grade = new Grader();
        assertEquals('S', grade.convertPointsToGrade(59));
    }
    @Test
    void fourtyShouldReturnI(){
        Grader grade = new Grader();
        assertEquals('I', grade.convertPointsToGrade(40));
    }
    @Test
    void seventytwoShouldReturnB(){
        Grader grade = new Grader();
        assertEquals('B', grade.convertPointsToGrade(72));
    }
    @Test
    void eightyfourShouldReturnT(){
        Grader grade = new Grader();
        assertEquals('T', grade.convertPointsToGrade(84));
    }
    @Test
    void ninetysevenShouldReturnE(){
        Grader grade = new Grader();
        assertEquals('E', grade.convertPointsToGrade(97));
    }
    @Test
    void eightyShouldReturnT(){
        Grader grade = new Grader();
        assertEquals('T', grade.convertPointsToGrade(80));
    }
    @Test
    void negativeOneShouldReturnIllegalArgumentException(){
        Grader grade = new Grader();
        assertThrows(IllegalArgumentException.class, () -> grade.convertPointsToGrade(-1));
    }
}