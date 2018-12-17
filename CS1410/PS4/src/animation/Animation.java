package animation;

import java.awt.Color;
import java.awt.Graphics;

public class Animation
{
    /**
     * This is the method that you need to rewrite to create a custom animation. This method is called repeatedly as the
     * animation proceeds. It needs to draw on g how the animation should look after time milliseconds have passed.
     * 
     * @param g Graphics object on which to draw
     * @param time Number of milliseconds that have passed since animation started
     */
    public static void paintFrame (Graphics g, int time, int height, int width)
    {
       // drawHouse1(g, time, 5, 400);
       // drawHouse2(g, time, 5, 200);
        drawBackground(g);
        drawNet(g);
        drawBall1(g, time, 150, 250);
        drawMan1(g, time, 100, 400);
        drawText(g, time);
        
    }
    /**
     * Draws the backboard and rim of the basket using java graphics and various shapes
     */
    public static void drawNet (Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(700, 100, 300, 200);
        g.setColor(Color.BLACK);
        g.fillRect(750, 150, 200, 150);
        g.setColor(Color.getHSBColor(62, 95, 100));
        g.fillRect(775, 175, 150, 125);
        g.setColor(Color.RED);
        g.fillOval(790, 265, 125, 50);
        g.setColor(Color.getHSBColor(62, 95, 100));
        g.fillOval(795, 270, 115, 40);
    }
    /**
     * draws and animates the text presented after basket is made using drawStrings
     */
    public static void drawText (Graphics g, int time)
    {
        if(time >=4000) {
        g.setColor(Color.BLACK);
        g.drawString("SWISH!", 500, time/8 - 500);
        g.drawString("SWISH!", 500, time/8 - 475);
        g.drawString("SWISH!", 500, time/8 - 450);
        g.drawString("SWISH!", 500, time/8 - 425);
        g.drawString("SWISH!", 500, time/8 - 400);
        g.drawString("SWISH!", 500, time/8 - 375);
        g.drawString("SWISH!", 500, time/8 - 350);
        }
    }
    /**
     * draws the background of the court and sets a brown color
     */
    public static void drawBackground (Graphics g)
    {
        g.setColor(Color.getHSBColor(62, 95, 100));
        g.fillRect(0, 0, 1500, 1500);
    }
    /**
     * Draws the basketball and animates it to a cosine curve by calling drawBasketball method.
     */
    public static void drawBall1 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 3000)
        {
            drawBasketball(g, initialX + time / 5, initialY + (int) (150 * Math.cos(time * Math.PI / 2000)), 1, Color.ORANGE);
        }
        if (time >=3000 && time <= 4000)
        {
            drawBasketball(g, 450 + time / 10, initialY + (int) (150 * Math.cos(time * Math.PI / 1000)), 1, Color.ORANGE);
        }
    }
    /**
     * Draws the static non-moving shooter by calling drawMan method
     */
    public static void drawMan1 (Graphics g, int time, int initialX, int initialY)
    {
        if(time < 20000)
        {
            drawMan(g, initialX, initialY, 1, Color.BLACK);
        }
    }
    /**
     * Draws the shape of the basketball using java graphics shapes and includes lines of basketball grip
     */
    public static void drawBasketball (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(Color.getHSBColor(39, 100, 79));
        g.fillOval(x, y, 50, 50);
        g.setColor(Color.BLACK);
        g.drawLine(x, y+25, x + 50, y+25);
        g.drawLine(x+25, y + 15, x+25, y+35);
        g.drawLine(x+15, y + 15, x+15, y+35);
        g.drawLine(x+35, y + 15, x+35, y+35);
    }
    /**
     * Draws the basketball shooter by using java graphics and various lines and shapes. 
     */
    public static void drawMan (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.drawLine(x, y, x, y+100);
        g.drawOval(x-25, y-50, (int) (scale * 50), (int) (scale * 50));
        g.drawLine(x-50, y+25, x+50, y+25);
        g.drawLine(x, y+100, x-25, y+150);
        g.drawLine(x, y+100, x+25, y+150);
    }
    
    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house moves from left to right for two seconds, after which it moves down. The house
     * starts out invisible and grows larger as time goes on. The position of the house at the beginning of the
     * animation is given with initialX and initialY.
     */
    public static void drawHouse1 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 2000)
        {
            drawHouse(g, initialX + time / 10, initialY, time / 2000., Color.BLUE);
        }
        else
        {
            int intermediateX = initialX + 2000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (time - 2000) / 10, time / 2000., Color.BLUE);
        }
    }

    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house follows a sine wave for three seconds, after which it moves up and down. The
     * position of the house at the beginning of the animation is given with initialX and initialY.
     */
    public static void drawHouse2 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 3000)
        {
            drawHouse(g, initialX + time / 10, initialY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
        else
        {
            int intermediateX = initialX + 3000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
    }

    /**
     * Draws a house on g whose upper-left corner is at coordinate (x,y) using the provided scaling factor and color.
     * The larger the scaling factor, the larger the house.
     */
    public static void drawHouse (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.drawRect(x, y, (int) (50 * scale), (int) (30 * scale));
        g.drawLine(x, y, x + (int) (25 * scale), y - (int) (15 * scale));
        g.drawLine(x + (int) (25 * scale), y - (int) (15 * scale), x + (int) (50 * scale), y);
    }
    

}
