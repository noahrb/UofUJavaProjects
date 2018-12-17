package cs1410;

import java.util.Scanner;

/**
 * A collection of methods for the third assignment of CS 1410.
 * 
 * @author Noah Beverly
 */
public class MethodLibrary
{
    private static Scanner s;

    /**
     * You can use this main method for experimenting with your methods if you like, but it is not part of the
     * assignment.
     */
    public static void main (String[] args)
    {
        makeRectangle(2,2);
    }

    /**
     * Returns the life stage of a person, given his or her age. The possible return values are "baby" (age is less than
     * 2), "child" (age is between 2 and 12 inclusive), "teen" (age is between 13 and 17 inclusive), "adult" (age is
     * between 18 and 64 inclusive), and "senior" (age is greater than 64).
     * 
     * Examples: getLifeStage(25) is "adult" and getLifeStage(0) is "baby".
     * 
     * IMPLEMENTATION NOTE: Use a 5-way conditional
     * 
     * @param age Must be non-negative
     */
    public static String getLifeStage (int age)
    {
        if (age < 2)
        {
            return "baby";
        }

        if (age >= 2 && age <= 12)
        {
            return "child";
        }

        if (age >= 13 && age <= 17)
        {
            return "teen";
        }

        if (age >= 18 && age <= 64)
        {
            return "adult";
        }

        if (age > 64)
        {
            return "senior";
        }
        else
        {
            return "";
        }

    };

    /**
     * Returns the index within s of the first vowel ('a', 'e', 'i', 'o', 'u' or an upper-case version) that occurs in
     * s. If there is no vowel in s, returns -1.
     * 
     * Examples: firstVowelIndex("Apple") is 0, firstVowelIndex("hello") is 1, firstVowelIndex("slope") is 2,
     * firstVowelIndex("strength") is 4, and firstVowelIndex("xyzzy") is -1.
     * 
     * IMPLEMENTATION NOTE: This method is already completely implemented. There is no need for you to change anything.
     */
    public static int firstVowelIndex (String s)
    {
        int i = 0;
        while (i < s.length())
        {
            if ("aeiouAEIOU".indexOf(s.charAt(i)) >= 0)
            {
                return i;
            }
            i = i + 1;
        }
        return -1;
    }

    /**
     * Returns the result of converting s to "Pig Latin". Convert a string s to Pig Latin by using the following rules:
     * 
     * (1) If s contains no vowels, do nothing to it.
     * 
     * (2) Otherwise, if s starts with a vowel, append "way" to the end.
     * 
     * (3) Otherwise, move everything up to (but not including) the first vowel to the end and add "ay".
     * 
     * For example, "hello" converts to "ellohay", "small" converts to "allsmay", "eat" converts to "eatway", and "nth"
     * converts to "nth".
     * 
     * IMPLEMENTATION NOTE: This will require a three-way conditional that extracts pieces of Strings and recombines
     * them into new Strings. For full credit (and an easier implementation), use the firstVowelIndex method provided
     * above in your method's implementation. You will also find the methods s.substring() and s.charAt() (where s is a
     * String), as well as the + operator that concatenates Strings, very useful.
     */
    public static String toPigLatin (String s)
    {
        char a = Character.toLowerCase(s.charAt(0));
        int firstVowelIndex = 0;
        int endOfWord = s.length();
        String pigString = s;
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
        {
            pigString = s + "way";
        }

        else if (a != 'a' && a != 'e' && a != 'i' && a != 'o' && a != 'u')
        {
            for (int i = 0; i < endOfWord; i++)
            {
                char b = Character.toLowerCase(s.charAt(i));
                if (b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u')
                {
                    firstVowelIndex = i;
                    pigString = s.substring(firstVowelIndex, endOfWord) + s.substring(0, firstVowelIndex) + "ay";
                    break;
                }

            }

        }
        return pigString;

    }

    /**
     * Returns the result of converting each token from words into Pig Latin and then appending the results, with each
     * converted word followed by a single space character. A token is a sequence of letters separated from other tokens
     * by white space. If there are no tokens, returns the empty string.
     * 
     * Examples: "" converts to "" and "This is a test" converts to "isThay isway away esttay ".
     * 
     * IMPLEMENTATION NOTE: Use a Scanner to split the string into individual tokens and use an accumulation loop to
     * consume the tokens. Make use of your toPigLatin() method.
     * 
     * @param words Must consist of only letters and white space
     */
    public static String convertToPigLatin (String words)
    {
        s = new Scanner(words);
        String convertedSentence = "";
        while (s.hasNext())
        {
            String token = s.next();

            convertedSentence = convertedSentence + toPigLatin(token) + " ";
        }
        return convertedSentence;

    }

    /**
     * Returns the sum of the nth roots of each double x in numbers, where numbers consists of zero or more double
     * tokens (separated by white space) and n is positive. A small around of roundoff error is to be expected.
     * 
     * Examples: sumOfRoots("1 4 9 16", 2) is 10 and sumOfRoots("") is 0.
     * 
     * @param numbers Must consist of only double literals and white space
     * @param n must be positive
     */
    public static double sumOfRoots (String numbers, int n)
    {
        s = new Scanner(numbers);
        double sum = 0.0;

        while (s.hasNext())
        {
            double token = s.nextDouble();
            sum = sum + Math.pow(token, 1.0 / n);
        }
        return sum;

    }

    /**
     * Reports whether or not every character in source occurs at least once in target.
     * 
     * Examples: containsAll("abc", "abracadabra") is true, and containsAll("def", "Defect") is false.
     * 
     * IMPLEMENTATION NOTE: Write this as an accumulation loop. Don't try to write a doubly-nested loop!
     */
    public static boolean containsAll (String source, String target)
    {
        boolean a = true;

        if (a == true)
        {
            for (int i = 0; i < target.length(); i++)
            {
                a = source.contains(target.substring(i));
            }
        }
        if (target.length()==0)
        {
            a = false;
        }
        if (target.length() == 0 && source.length() == 0)
        {
            a = true;
        }

        return a;
    }

    /**
     * Returns a String of length width that begins and ends with the character edge and contains width-2 copies of the
     * character inner in between. The method does not print anything.
     * 
     * Example makeLine('+', '-', 8) is "+------+".
     * 
     * IMPLEMENTATION NOTE: Use an accumulation loop
     * 
     * @params width must be >= 2
     */
    public static String makeLine (char edge, char inner, int width)
    {
        String line = "";
        for(int i=0; i<width; i++)
        {
          line = line + inner;  
        }
        for(int i=0; i<1; i++)
        {
            line = String.valueOf(edge) + line.substring(2, line.length()) + String.valueOf(edge);
        }
        return line;
    }

    /**
     * Returns a string which, when printed out, will be a rectangle shaped something like this:
     * 
     * <pre>
     * +----------+
     * |          |
     * |          |
     * |          |
     * |          |
     * |          |
     * +----------+
     * </pre>
     * 
     * The returned string should consist of height lines, each ending with a newline. In addition to the newline, the
     * first and last lines should begin and end with '+' and should contain width-2 '-' symbols. In addition to the
     * newline, the other lines should begin and end with '|' and should contain width-2 spaces. The method does not
     * print anything.
     * 
     * IMPLEMENTATION NOTE: For full credit (and for easier implementation), make use of the makeLine method in your
     * implementation of makeRectangle. Use an accumulation loop.
     * 
     * @param height must be >= 2
     * @param width must be >= 2
     */
    public static String makeRectangle (int height, int width)
    {
        String a = "";
           
               for(int i = 0; i<height-2; i++)
               {
                  a = a + makeLine('|', ' ', width) + "\n";
               }
              /* if(height <=2 && width <= 2)
               {
                   a = "++\n++\n";
               } */
             //  String b =  makeLine('|', ' ', width) + "\n" + a + makeLine('|', ' ', width);
       
            return makeLine('+', '-', width) + "\n" + a + makeLine('+', '-', width) + "\n";
    }

    /**
     * Returns the integer that follows n in a Hailstone sequence. If n is 1, returns 1. Otherwise, returns either n/2
     * (if n is even) or 3n+1 (if n is odd).
     * 
     * Examples: nextHailstone(1) is 1, nextHailstone(7) is 22, and nextHailstone(6) is 3,
     * 
     * IMPLEMENTATION NOTE: This one will require a three-way conditional
     * 
     * @param n must be positive
     */
    public static int nextHailstone (int n)
    {
        return 0;
    }

    /**
     * Returns a string consisting of a Hailstone sequence beginning with the positive integer n and ending with 1. The
     * string should consist of a sequence of numerals, with each numeral followed by a single space. When a numeral m
     * (other than 1) appears in the sequence, it should be followed by nextHailstone(m).
     * 
     * Examples: nextHailstone(1) is "1 " and nextHailstone(5) is "5 16 8 4 2 1 ".
     * 
     * IMPLEMENTATION NOTE: Make use of your nextHailstone method. Use an accumulation loop, but with a bit of a twist.
     * 
     * @param n must be positive
     */
    public static String hailstones (int n)
    {
        return "";
    }

    /**
     * Reports whether or not s1 and s2 contain exactly the same tokens in the same order.
     * 
     * Examples: <br>
     * sameTokens("this is a test", " this is a test ") is true <br>
     * sameTokens("", "") is true <br>
     * sameTokens("hello there", "hello there Joe") is false <br>
     * sameTokens("abc def", "def abc") is false <br>
     * sameTokens("a", "A") is false
     *
     * IMPLEMENTATION NOTE: You can write this as an accumulation loop with a twist. Be aware of the implications of the
     * short-circuited evaluation of &&.
     */
    public static boolean sameTokens (String s1, String s2)
    {

        return false;
    }
}
