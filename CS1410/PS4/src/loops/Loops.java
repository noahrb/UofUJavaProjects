package loops;

import java.util.Scanner;

public class Loops
{
    private static Scanner scnr;

    /**
     * takes a String s and a String t as parameters, and returns a boolean. It returns true if t occurs as a token
     * within s, and returns false otherwise. (Implementation note: Use a Scanner to break s into tokens, and use a
     * searching loop to look for t.)
     *
     */
    public static boolean containsToken (String s, String t)
    {
        boolean containsToken = false;
        scnr = new Scanner(s);
        while (scnr.hasNext())
        {
            String token = scnr.next();
            if (token.equals(t))
            {
                containsToken = true;
            }
        }
        return containsToken;
    }

    /**
     * takes a Scanner scn and a String t as parameters, and returns a String. It returns a line from scn that contains
     * t as a token (if such a line exists) and returns the empty string (otherwise). (Implementation note: You'll find
     * the containsToken method specified above helpful. This method calls for a searching loop.)
     */
    public static String findLineWithToken (Scanner scn, String t)
    {
        String result = "";
        while (scn.hasNext())
        {
            String a = scn.nextLine();
            if (containsToken(a, t))
            {
                result = a;
            }
        }
        return result;

    }

    /**
     * takes a String s as a parameter and returns a boolean. It returns true if s reads the same forwards and
     * backwards, and returns false otherwise. You are not allowed to use any method that reverses s. (Implementation
     * note: Use a loop that searches for a character in s that doesn't "match up" with an identical character elsewhere
     * in the string. You'll need to determine the appropriate definition of "match up".)
     */
    public static boolean isPalindrome (String s)
    {
        boolean isPalindrome = true;
        int a = s.length() - 1;
        int b = 0;
        while (a > b)
        {
            if (s.charAt(a) != s.charAt(b))
            {
                isPalindrome = false;
            }
            a--;
            b++;
        }

        if (s.length() == 0 || s.length() == 1)
        {
            isPalindrome = true;
        }
        return isPalindrome;
    }

    /**
     * takes a Scanner scn as its parameter and returns a String. It returns the longest token from scn that is a
     * palindrome (if one exists) or the empty string (otherwise). (Implementation note: You'll find the isPalindrome
     * method helpful here. This method calls for an optimization loop.)
     */
    public static String findLongestPalindrome (Scanner scn)
    {
        String longestPalindrome = "";

        while (scn.hasNext())
        {
            String test = scn.next();
            // longestPalindrome = scn.next();
            if (isPalindrome(test))
            {
                // String test = scn.next();
                if (test.length() > longestPalindrome.length())
                {
                    longestPalindrome = test;
                }
            }
        }
        return longestPalindrome;
    }

    /**
     * takes a Scanner scn as its parameter and returns an int. It finds the line in scn with the most whitespace
     * characters and returns the number of whitespace characters it contains (if scn contains at least one line) or -1
     * (otherwise). (Implementation note: This method calls for an optimization loop, and will be be easy if you first
     * implement a method that counts the amount of whitespace in a string. There is a method Character.isWhitespace()
     * that you'll find useful.)
     */
    public static int findMostWhitespace (Scanner scn)
    {
        int whiteSpaceCount = 0;
        int mostWhitespaceLine = -1;
        while (scn.hasNextLine())
        {
            String a = scn.nextLine();
            int i = 0;
            whiteSpaceCount = 0;
            while (i < a.length())
            {
                if (Character.isWhitespace(a.charAt(i)))
                {
                    whiteSpaceCount = whiteSpaceCount + 1;
                }
                i = i + 1;
            }
            if (whiteSpaceCount > mostWhitespaceLine)
            {
                mostWhitespaceLine = whiteSpaceCount;
            }
        }
           
        return mostWhitespaceLine;
    }
}
