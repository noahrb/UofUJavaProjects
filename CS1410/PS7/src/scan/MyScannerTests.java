package scan;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyScannerTests
{
    /**
     * tests checks if the input has a next token.
     */
    @Test
    public void hasNextTest ()
    {
        MyScanner a = new MyScanner("abc cd ald");
        assertEquals(true, a.hasNext());
    }
    
    /**
     * checks to see if MyScanner returns next token
     */
    @Test
    public void nextTest ()
    {
       MyScanner a = new MyScanner("abc cd ald");
       assertEquals("abc", a.next());
    }
    
    
    
}
