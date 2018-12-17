package cs1410;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Methods in support of PS6.
 * 
 * @author Noah Beverly and Joe Zachary
 */
public class GraphingMethods
{
    /**
     * Constant used to request a max operation
     */
    public final static int MAX = 0;

    /**
     * Constant used to request a min operation
     */
    public final static int MIN = 1;

    /**
     * Constant used to request a sum operation
     */
    public final static int SUM = 2;

    /**
     * Constant used to request an average operation
     */
    public final static int AVG = 3;

    /**
     * The dataSource must consist of one or more lines. If there is not at least one line, the method throws an
     * IllegalArgumentException whose message explains what is wrong.
     * 
     * Each line must consist of some text (a key), followed by a tab character, followed by a double literal (a value),
     * followed by a newline.
     * 
     * If any lines are encountered that don't meet this criteria, the method throws an IllegalArgumentException whose
     * message explains what is wrong.
     * 
     * Otherwise, the map returned by the method (here called categoryMap) must have all of these properties:
     * 
     * (1) The set of keys contained by categoryMap must be the same as the set of keys that occur in the Scanner
     * 
     * (2) The list valueMap.get(key) must contain exactly the same numbers that appear as values on lines in the
     * Scanner that begin with key. The values must occur in the list in the same order as they appear in the Scanner.
     * 
     * For example, if the Scanner contains
     * 
     * <pre>
     * Utah        10
     * Nevada       3
     * Utah         2
     * California  14
     * Arizona     21
     * Utah         2
     * California   7
     * California   6
     * Nevada      11
     * California   1
     * </pre>
     * 
     * (where the spaces in each line are intended to be a single tab), then this map should be returned:
     * 
     * <pre>
     *  Arizona    {21}
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     */
    public static TreeMap<String, ArrayList<Double>> readTable (Scanner dataSource)
    {
        TreeMap<String, ArrayList<Double>> map = new TreeMap<>();

//        if (hasCorrectFormatting(dataSource))
//        {
            while (dataSource.hasNextLine())
            {
                String i = dataSource.nextLine();
                if (i.substring(0).contains("\n") || i.equals("") || !i.substring(i.length()).contains("")
                        || !i.contains("\t"))
                {
                    throw new IllegalArgumentException();
                }
                
                String line = dataSource.nextLine();
                String[] lineParts = line.split("\t", 2);
//                Arrays.stream(lineParts).forEach(System.out::println);
                String key = lineParts[0];
                double number = Double.parseDouble(lineParts[1]);
//                System.out.println(lineParts[0]);
//                System.out.println(lineParts[1]);
                if (map.containsKey(key) == false)
                {
                    ArrayList<Double> list = new ArrayList<>();
                    list.add(number);
                    map.put(key, list);
                } else {
                    map.get(key).add(number);
                }
                System.out.print(map);
            }
//        }
        return map;
    }

    private static boolean hasCorrectFormatting (Scanner dataSource)
    {
        boolean isCorrect = true;
        // while(dataSource.hasNext())
        // {
        // String g = dataSource.next();
        // if(g.equals(""))
        // {
        // throw new IllegalArgumentException();
        // }
        // }
        while (dataSource.hasNextLine())
        {
            String i = dataSource.nextLine();
            if (i.substring(0).contains("\n") || i.equals("") || !i.substring(i.length()).contains("")
                    || !i.contains("\t"))
            {
                throw new IllegalArgumentException();
            }
        }
        return isCorrect;
    }

    /**
     * If categoryMap is of size zero, throws an IllegalArgumentException whose message explains what is wrong.
     * 
     * Else if any of the values in the category map is an empty set, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Else if any of the numbers in the categoryMap is not positive, throws an IllegalAgumentException whose message
     * explains what is wrong.
     * 
     * Else if operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException whose message
     * explains what is wrong.
     *
     * Else, returns a TreeMap<String, Double> (here called summaryMap) such that:
     * 
     * (1) The sets of keys contained by categoryMap and summaryMap are the same
     * 
     * (2) For all keys, summaryMap.get(key) is the result of combining the numbers contained in the set
     * categoryMap.get(key) using the specified operation. If the operation is MAX, "combining" means finding the
     * largest of the numbers. If the operation is MIN, "combining" means finding the smallest of the numbers. If the
     * operation is SUM, combining means summing the numbers. If the operation is AVG, combining means averaging the
     * numbers.
     * 
     * For example, suppose the categoryMap maps like this:
     * 
     * <pre>
     *  Arizona    {21
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     * 
     * and the operation is SUM. The map that is returned must map like this:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     */
    public static TreeMap<String, Double> prepareGraph (TreeMap<String, ArrayList<Double>> categoryMap, int operation)
    {
        // This implementation ignores its parameters and returns the map described in the Javadoc.
        TreeMap<String, Double> summaryMap = new TreeMap<>();
        summaryMap.put("Arizona", 21.0);
        summaryMap.put("Arizona", 28.0);
        summaryMap.put("Arizona", 14.0);
        summaryMap.put("Arizona", 21.0);
        return summaryMap;
    }

    /**
     * If colorMap is empty, throws an IllegalArgumentException.
     * 
     * If there is a key in colorMap that does not occur in summaryMap, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * If any of the numbers in the summaryMap is non-positive, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Otherwise, displays on g the subset of the data contained in summaryMap that has a key that appears in colorMap
     * with either a pie chart (if usePieChart is true) or a bar graph (otherwise), using the colors in colorMap.
     * 
     * Let SUM be the sum of all the values in summaryMap whose keys also appear in colorMap, let KEY be a key in
     * colorMap, let VALUE be the value to which KEY maps in summaryMap, and let COLOR be the color to which KEY maps in
     * colorMap. The area of KEY's slice (in a pie chart) and the length of KEY's bar (in a bar graph) must be
     * proportional to VALUE/SUM. The slice/bar should be labeled with both KEY and VALUE, and it should be colored with
     * COLOR.
     * 
     * For example, suppose summaryMap has this mapping:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     * 
     * and colorMap has this mapping:
     * 
     * <pre>
     *  California Color.GREEN
     *  Nevada     Color.BLUE
     *  Utah       Color.RED
     * </pre>
     * 
     * Since Arizona does not appear as a key in colorMap, Arizona's entry in summaryMap is ignored.
     * 
     * In a pie chart Utah and Nevada should each have a quarter of the pie and California should have half. In a bar
     * graph, California's line should be twice as long as Utah's and Nevada's. Utah's slice/bar should be red, Nevada's
     * blue, and California's green.
     * 
     * The method should display the pie chart or bar graph by drawing on the g parameter. The example code below draws
     * both a pie chart and a bar graph for the situation described above.
     */
    public static void drawGraph (Graphics g, TreeMap<String, Double> summaryMap, TreeMap<String, Color> colorMap,
            boolean usePieChart)
    {
        // This implementation ignores its parameters (except for usePieChart) and draws a pie chart or a bar graph
        // for the data described in the Javadoc.

        final int TOP = 10;        // Offset of graph from top edge
        final int LEFT = 10;       // Offset of graph from left edge
        final int DIAM = 300;      // Diameter of pie chart
        final int WIDTH = 10;      // Width of bar in bar chart

        // Draw a pie chart if requested
        if (usePieChart)
        {
            // Draw the entire circle California's color, plus its label
            g.setColor(Color.GREEN);
            g.fillArc(LEFT, TOP, DIAM, DIAM, 0, 360);
            g.fillRect(LEFT + DIAM + 2 * WIDTH, TOP, WIDTH, WIDTH);
            g.setColor(Color.black);
            g.drawString("California 28.00", LEFT + DIAM + 4 * WIDTH, TOP + WIDTH);

            // Draw Nevada's quarter wedge, plus its label
            g.setColor(Color.BLUE);
            g.fillArc(LEFT, TOP, DIAM, DIAM, 180, 90);
            g.fillRect(LEFT + DIAM + 2 * WIDTH, TOP + 2 * WIDTH, WIDTH, WIDTH);
            g.setColor(Color.black);
            g.drawString("Nevada 14.00", LEFT + DIAM + 4 * WIDTH, TOP + 3 * WIDTH);

            // Draw Utah's half wedge, plus its label
            g.setColor(Color.RED);
            g.fillArc(LEFT, TOP, DIAM, DIAM, 270, 90);
            g.fillRect(LEFT + DIAM + 2 * WIDTH, TOP + 4 * WIDTH, WIDTH, WIDTH);
            g.setColor(Color.black);
            g.drawString("Utah 14.00", LEFT + DIAM + 4 * WIDTH, TOP + 5 * WIDTH);
        }

        // Draw a bar chart if requested
        else
        {
            // Draw California's bar, plus its label
            g.setColor(Color.GREEN);
            g.fillRect(LEFT + DIAM - DIAM / 2, TOP, DIAM / 2, 2 * WIDTH);
            g.setColor(Color.black);
            g.drawString("California 28.00", LEFT + DIAM + 2 * WIDTH, TOP + WIDTH + WIDTH / 2);

            // Draw Nevada's bar, plus its label
            g.setColor(Color.BLUE);
            g.fillRect(LEFT + DIAM - DIAM / 4, TOP + 3 * WIDTH, DIAM / 4, 2 * WIDTH);
            g.setColor(Color.black);
            g.drawString("Nevada 14.00", LEFT + DIAM + 2 * WIDTH, TOP + 4 * WIDTH + WIDTH / 2);

            // Draw Utah's bar, plus its label
            g.setColor(Color.RED);
            g.fillRect(LEFT + DIAM - DIAM / 4, TOP + 6 * WIDTH, DIAM / 4, 2 * WIDTH);
            g.setColor(Color.black);
            g.drawString("Utah 14.00", LEFT + DIAM + 2 * WIDTH, TOP + 7 * WIDTH + WIDTH / 2);
        }
    }
}
