package c4;
import static org.junit.Assert.*;
import org.junit.Test;

public class C4Tests
{
    /**
     * checks for an illegal argument exception when a new C4Board is created with a value in rows less than 4.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExceptions1()
    {
        C4Board a = new C4Board(3, 6);
    }
    
    /**
     * checks that a new game is created without an exception.
     */
    @Test
    public void testC4Board()
    {
        C4Board a = new C4Board(5, 5);
        a.newGame();
    }
    
    /**
     * checks for tile count when no tiles are placed.
     */
    @Test
    public void test3()
    {
        C4Board a = new C4Board(7, 8);
        a.newGame();
        assertEquals(0, a.getTies());
    }
    
    /**
     * checks that a P1 token is placed when the 2nd coloumn is chosen.
     */
    @Test
    public void test4()
    {
        C4Board a = new C4Board(5,6);
        a.moveTo(1);
    }
    
    /**
     * checks that tile count goes up by 2 after two tiles should be placed.
     */
    @Test
    public void test5()
    {
        C4Board a = new C4Board(7,8);
        a.moveTo(2);
        a.moveTo(3);
        assertEquals(2, a.getTileCount());
    }
    
    /**
     * Checks for an exception thrown if searching for a value that doens't exist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test6()
    {
        C4Board a = new C4Board(5, 5);
        a.getOccupant(7, 9);
    }
}
