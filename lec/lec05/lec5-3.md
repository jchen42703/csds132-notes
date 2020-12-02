# While Loop Example 1:  Euclid's Greatest Common Divisor algorithm

We want to compute the greatest common divisor of two positive integers: a and b

If a % b = 0 then the greatest common divisor is b
Otherwise gcd(a,b) = gcd(b, a % b)

This gives us a loop.  For example:

```
	gcd(30, 12) = gcd(12, 6) = 6
	gcd(24, 15) = gcd(15, 9) = gcd(9, 6) = gcd(6, 3) = 3

  	public static int gcd(int a, int b) {
	  while (a % b ! = 0) {
	    int r = a % b;
	    a = b;
	    b = r;
	  }
	  return b;
	}
```

This algorithm seems to require a > b.  (It does not, trace the loop and see what happens if b > a.)
