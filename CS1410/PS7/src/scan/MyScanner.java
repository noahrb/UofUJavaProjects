package scan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyScanner
{
    private List<String> list;
    private int i;
    /**
     * MyScanner object constructor
     */
    public MyScanner (String input)
    {
        String a = input;
        list = Arrays.asList(a.split("\\s+"));
        String b[] = a.split("\\s+");
        int i = 0;
        
        
    }
    
    /**
     * reports whether the input has another token
     */
    public boolean hasNext()
    {
        if(list.get(i+1) != null)
        {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * returns the input's next token
     */
    public String next()
    { 
        i = i+1;
            return list.get(i-1);

    }
    
    /**
     * reports if the input has another int
     */
    public boolean hasNextInt()
    {
//        if(list.get(i+1) != null && Integer.parseInt(list.get(i+1))
//        {
//            
//        }
        return true;
    }
    
    /**
     * returns the input's next int
     */
    public int nextInt()
    {
        return 0;
    }
}
