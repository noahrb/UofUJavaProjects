package cs1410;

/**
 * Represents a variety of information about a geocache. A geocache has a title, an owner, a difficulty rating, a
 * terrain rating, a GC code, a latitude, and a longitude.
 */
public class Cache
{
    // TODO: Put representation here
    public String a = "";
    private String owner;
    private String title;
    private double difficulty;
    private double terrain;
    private String gcCode;
    private String latitude;
    private String longitude;

    /**
     * Creates a Cache from a string that consists of these seven cache attributes: the GC code, the title, the owner,
     * the difficulty rating, the terrain rating, the latitude, and the longitude, in that order, separated by single
     * TAB ('\t') characters.
     * 
     * If any of the following problems are present, throws an IllegalArgumentException:
     * <ul>
     * <li>Fewer than seven attributes</li>
     * <li>More than seven attributes</li>
     * <li>A GC code that is anything other than "GC" followed by one or more upper-case letters and/or digits</li>
     * <li>A difficulty or terrain rating that parses to anything other than the doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5,
     * or 5.</li>
     * <li>A title, owner, latitude, or longitude that consists only of white space</li>
     */
    public Cache (String attributes) throws IllegalArgumentException
    {
        a = attributes;
        //Cache cache = new Cache(attributes);
        String d[] = a.split("\t");
        if(d.length != 7 || !d[0].substring(0, 2).equals("GC"))
        {
            throw new IllegalArgumentException();
        }
        char e = d[0].charAt(2);
        if(Character.isLowerCase(e))
        {
            if(!Character.isDigit(e))
            {
                throw new IllegalArgumentException();
            }
        }
        
        double f = Double.parseDouble(d[3]);
        if(f != 1 && f != 1.5 && f != 2 && f != 2.5 && f != 3 && f != 3.5 && f != 4 && f != 4.5 && f != 5)
        {
            throw new IllegalArgumentException();
        }
        
        f = Double.parseDouble(d[4]);
        if(f != 1 && f != 1.5 && f != 2 && f != 2.5 && f != 3 && f != 3.5 && f != 4 && f != 4.5 && f != 5)
        {
            throw new IllegalArgumentException();
        }
        
        owner = getOwner();
        title = getTitle();
        difficulty = getDifficulty();
        terrain = getTerrain();
        gcCode = getGcCode();
        latitude = getLatitude();
        longitude = getLongitude();
        
        
    }

    /**
     * Converts this cache to a string
     */
    public String toString ()
    {
        return getTitle() + " by " + getOwner();
    }

    /**
     * Returns the owner of this cache
     */
    public String getOwner ()
    {
        String b[] = a.split("\t");

        return b[2];
    }

    /**
     * Returns the title of this cache
     */
    public String getTitle ()
    {
        String b[] = a.split("\t");
        return b[1];
    }

    /**
     * Returns the difficulty rating of this cache
     */
    public double getDifficulty ()
    {
        String b[] = a.split("\t");
        Double c = Double.parseDouble(b[3]);
        return c;
    }

    /**
     * Returns the terrain rating of this cache
     */
    public double getTerrain ()
    {
        String b[] = a.split("\t");
        Double c = Double.parseDouble(b[4]);
        return c;
    }

    /**
     * Returns the GC code of this cache
     */
    public String getGcCode ()
    {
        String b[] = a.split("\t");
        return b[0];
    }

    /**
     * Returns the latitude of this cache
     */
    public String getLatitude ()
    {
        String b[] = a.split("\t");
        return b[5];
    }

    /**
     * Returns the longitude of this cache
     */
    public String getLongitude ()
    {
        String b[] = a.split("\t");
        return b[6];
    }

}
