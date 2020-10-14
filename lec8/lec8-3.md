# Java Shortcuts

## Binary Operator shortcuts:
```
i += 3    <- shorthand for i = i + 3
     This shortcut works for all binary operators:
x /= 10   <- shorthand for x = x / 10
```

## Conditional Operator
`condition ? value1 : value2`
* if condition is true, the result is `value1`.  Otherwise the result is `value2`
  * Instead of:
  ```
  	   if (x <= 10)
  	     y = 5;
  	   else
  	     y = -5;
  ```
  	We can write
  	    `y = (x <= 10) ? 5 : -5;`
  * Instead of:
  ```
	   if (x < 0)
	     return -1 * x;
	   else
	     return x;
  ```
	We can write
	   `return x < 0 ? -1 * x : x;`

## Switch Statements
Multiple if statements that test equality of values:
```
  if (month == 1)
    days = 31;
  else if (month == 2)
    days = 28;
  else if (month == 3)
    days = 31;
```

```
	switch (month) {
	  case 1:
	    days = 31;
	    break;
	  case 2:
	    days = 28;
	    break;
```
__or__
```
	switch (month) {
	  case 1:
	  case 3:
	  case 5:
	  case 7:
	  case 8:
	  case 10:
	  case 12:
	    days = 31;
	    break;
	  case 2:
	    days = 28;
	    break;
	  default:
	    days = 30;
	    break;
	}
```

Note that `break` is used to exit execution.  If a `break` is omitted on a case, execution "falls through" to the next case.

Important: the __`switch` statement uses == to test equality.__  This works well with primitive, and it can work with objects __as long as you are testing whether two objects are the same address.__ (Exception, starting in Java 7, the case will test the contents of String).

## Increment and Decrement Shortcuts
```
i++   <- increments i and then returns the original value of i
++i   <- increments i by 1 and then returns the new value of i
i--   <- same but with a decrement by 1
--i
```

__Be careful that you use the ++ shortcut correctly!  i++ and ++i have different effects.__
```
i = 5;
i = ++i;
```
1. i is incremented to 6
2. the value of i is returned and stored into i
The result is that i stores 6.

```
i = 5;
i = i++;
```
1. The value of `i` is returned and remembered for when the right side is done evaluating
2. The value of `i` is incremented to 6
3. The original remembered value of `i` is stored into `i`. The result is that `i` stored 5.

### Increment and loops
```
	for (int i = 0; i < a.length; i++)
	  a[i] = i;

	for (int i = 0; i < a.length; ++i)
	  a[i] = i;
```

Both of the above loops do the same thing. a is assigned {0, 1, 2, 3, ...}

```
	int i = 0;
	while (i < a.length)
	  a[i++] = i;

 	int i = 0;
	while (i < a.length)
	  a[++i] = i;
```
These two loops are different.  The first assigns to a {1, 2, 3, ...}.  The second skips over the first element to give {*, 1, 2, 3, ...} but then crash when it tries to store a value in `a[a.length]`.

Here are two more:
```
	int i = 0;
	while (i < a.length)
	  a[i] = ++i;

	int i = 0;
	while (i < a.length)
	  a[i] = i++;
```
The first assigns to a {1, 2, 3, 4, ...} and the second assigns to a {0, 1, 2, 3, ...}

What does this do?
```
	int i = 0;
	while (i < a.length-1)
	  a[i] += ++a[++i];
```
