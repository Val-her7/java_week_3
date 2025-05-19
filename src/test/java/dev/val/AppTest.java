package dev.val;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    void twoPlusTwoShouldEqualFour(){
        assertEquals(4, App.add(2,2));
    }

    @Test
    void fivePlusTwoShouldEqualSeven(){
        assertEquals(7, App.add(5,2));
    }
}
