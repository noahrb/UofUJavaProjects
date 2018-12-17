package cs1410;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

public class CacheListTests
{
    /**
     * An example test for CacheList objects
     */
    @Test
    public void test () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    
    /**
     * Tests use of ownerConstraint method
     */
    @Test
    public void testOwnerConstraint () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("bunny");
        ArrayList<Cache> selected = clist.select();
        assertEquals("bunny", selected.get(0).getOwner());
    }
    
    /**
     * Tests use of difficulty constraint method
     */
    @Test
    public void testDiffConstraint () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setDifficultyConstraints(2, 4);
        ArrayList<Cache> selected = clist.select();
        assertEquals("GCRQWK", selected.get(0).getGcCode());
    }
    
    /**
     * Tests use of terrain constraint method
     */
    @Test
    public void testTerrainConstraint () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTerrainConstraints(2, 4);
        ArrayList<Cache> selected = clist.select();
        assertEquals("GCRQWK", selected.get(0).getGcCode());
    }
}
