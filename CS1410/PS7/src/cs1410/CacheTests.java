package cs1410;

import static org.junit.Assert.*;
import org.junit.Test;

public class CacheTests
{
    /**
     * tests that returns gc code of example cache
     */
    @Test
    public void getGCTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCRQWK", c.getGcCode());
    }
    
    /**
     *  checks for the Cache method to return owner
     */
    @Test
    public void getOwnerTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("geocadet", c.getOwner());
    }
    
    /**
     *  checks for the Cache method to return title of cache
     */
    @Test
    public void getTitleTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");

        assertEquals("Old Three Tooth", c.getTitle());
    }
    
    /**
     *  checks for the Cache method to return difficulty of cache
     */
    @Test
    public void getDifficultyTest ()
    {
        
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        String a = Double.toString(c.getDifficulty());
        assertEquals("3.5", a);
    }
    
    /**
     *  checks for the Cache method to return terrain of cache
     */
    @Test
    public void getTerrainTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        String a = Double.toString(c.getTerrain());
        assertEquals("3.0", a);
    }
    
    /**
     *  checks for the Cache method to return latitude of cache
     */
    @Test
    public void getLatitudeTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("N40 45.850", c.getLatitude());
    }
    
    /**
     *  checks for the Cache method to return longitude of cache
     */
    @Test
    public void getLongitudeTest ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("W111 48.045", c.getLongitude());
    }
    
    /**
     * checks if throws exception when new cache has less then seven attributes
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest1 ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850");
    }
    
    /**
     * checks if throws exception when new cache has more then seven attributes
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest2 ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\tcats");
    }
    
    /**
     * checks if throws exception when the GC code does not start with GC
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest3 ()
    {
        Cache c = new Cache("GDRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");        
    }
    
    /**
     * checks if throws exception when the GC code does not start with GC and is followed by an uppercase digit or number
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest4 ()
    {
        Cache c = new Cache("GCrQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");        
    }
    
    /**
     * checks if throws exception when the difficulty rating parses to anything other then doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5 or 5
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest5 ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.7\t3\tN40 45.850\tW111 48.045");
        
    }
    
    /**
     * checks if throws exception when the terrain rating parses to anything other then doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5 or 5
     */
    @Test(expected = IllegalArgumentException.class)
    public void formatTest6 ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t2.25\tN40 45.850\tW111 48.045");
        
    }
}
