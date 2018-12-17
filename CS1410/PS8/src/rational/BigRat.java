package rational;

import java.math.BigInteger;

/**
 * Provides rational number (fraction) objects. The rational arithmetic provided by Rational objects is subject to
 * integer overflow if the numerator and/or denominator becomes too large.
 * 
 * @param <BigNumber>
 */
public class BigRat
{
    private BigInteger num;
    private BigInteger den;

    /**
     * Creates the rational number 0
     */
    public BigRat ()
    {
        num = BigInteger.valueOf(0);
        den = BigInteger.valueOf(1);
    }

    /**
     * Creates the rational number n
     */
    public BigRat (long n)
    {
        num = BigInteger.valueOf(n);
        den = BigInteger.valueOf(1);
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public BigRat (long n, long d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException();
        }

        // Deals with signs
        if (d < 0)
        {
            d = -d;
            n = -n;
        }

        // Deal with lowest terms
        long g = gcd(Math.abs(n), d);
        BigInteger a = BigInteger.valueOf(n);
        BigInteger b = BigInteger.valueOf(g);
        BigInteger c = BigInteger.valueOf(d);
        num = a.divide(b);
        den = c.divide(b);
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public BigRat (BigInteger r, BigInteger d)
    {
        if (d.equals(BigInteger.valueOf(0)))
        {
            throw new IllegalArgumentException();
        }
        num = r;
        den = d;
        // bigRat = r.divide(d);
    }

    /**
     * Returns the sum of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.add(y); a/b + c/d = (ad +
     * bc) / bd
     */
    public BigRat add (BigRat r)
    {
        BigInteger x = this.num.multiply(r.den).add(this.den.multiply(r.num));
        BigInteger y = this.den.multiply(r.den);
        return new BigRat(x, y);
    }

    /**
     * Returns the difference of this and r a/b - c/d = (ad - bc) / bd
     */
    public BigRat sub (BigRat r)
    {
        BigInteger x = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
        BigInteger y = this.den.multiply(r.den);
        return new BigRat(x, y);
    }

    /**
     * Returns the product of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.mul(y); a/b * c/d =
     * ac/bd
     */
    public BigRat mul (BigRat r)
    {
        return new BigRat(this.num.multiply(r.num), this.den.multiply(r.den));
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the quotient of this and r. a/b / c/d = ad /
     * bc
     */
    public BigRat div (BigRat r)
    {
        if (r.num.equals(BigInteger.valueOf(0)))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            BigRat a = new BigRat(this.num.multiply(r.den), this.den.multiply(r.num));
            // BigInteger n = num.gcd(den);
            // BigInteger m = num.
            // BigRat b = new BigRat(
            return a;
        }
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive number if this > r To compare a/b and c/d,
     * compare ad and bc
     */
    public int compareTo (BigRat r)
    {
        BigInteger a = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
        // long diff = this.num * r.den - this.den * r.num;
        if (a.compareTo(BigInteger.valueOf(0)) < 0)
        {
            return -1;
        }
        else if (a.compareTo(BigInteger.valueOf(0)) > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns a string version of this in simplest and lowest terms. Examples: 3/4 => "3/4" 6/8 => "3/4" 2/1 => "2" 0/8
     * => "0" 3/-4 => "-3/4"
     */
    public String toString ()
    {
        if (den.equals(BigInteger.valueOf(1)))
        {
            return num + "";
        }
        else
        {
            BigInteger x = num.gcd(den);
            num = num.divide(x);
            den = den.divide(x);

            if (den.intValue() < 0)
            {
                den = den.abs();
                num = num.negate();
            }

            if (den.equals(BigInteger.valueOf(1)))
            {
                return num + "";                
            }
            else
            {
                return num + "/" + den;
            }
        }
    }

    /**
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     */
    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
