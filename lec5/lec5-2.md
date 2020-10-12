# Introduction to Loops
## Summary
* __The `while` loop:__
  ```
  	while (condition)
  	   loop-body-statement

        loop-body-statement is a single statement, simple or compound
        condition is any boolean expression
  ```
* __The `for` loop__
  ```
    for (initial statement; condition; increment statements)
       loop-body-statement
  ```
  * __Note the encapsulation in a compound statement.  This means that any variables declared in the for statement will not exist once the for statement completes.__
* __The `do-while` loop__
  ```
     do {
        loop-body-statement(s)
     } while (condition);
  ```
  * The do-while loop should be avoided except in cases where you really need the body of the loop to execute before testing the condition.
* `while` v. `for` loop
  * `for` loop when the loop is controlled by variables (i.e. array length) and there is a constant increment (i.e. `i++` or `i += 2`).
  * `while` loop when the increment is more complicated

## Introduction
Loops are a technique that allow us to execute a statement many times.  This is what gives a computer program its true power because computers can execute statements in loops millions of times a second.

There are 4 basic loop structures in Java.
1. while loop
2. for loop
3. do-while loop
4. foreach loop
The first three are general purpose loops.  The fourth is a special loop form that can only be used with arrays and `Iterable` types.  We will cover `foreach` loops later in the term.

## The while loop:
```
	while (condition)
	   loop-body-statement

      loop-body-statement is a single statement, simple or compound
      condition is any boolean expression
```

### While loop behavior:
1. the condition is evaluated
2. if the condition is true:
    1. the loop body statement is executed
    2. repeat step 1
if the condition is false, go to the next statement of the program

__Example 1: A silly example that prints "Hello" forever:__

```
	while (true)
	  System.out.println("Hello");
```

Note that we can put anything that evaluates to a boolean inside the parentheses.

__Example 2: A loop that prints "Hello" never:__

```
	while (false)
	  System.out.println("Hello");
```

__Example 3: An example that prints "Hello" 5 times:__
We need to keep track of how many times we have printed "Hello".  So we need a variable to store that number.

```
	int count = 0;
	// count stores the number of times we have printed "Hello"
	while (count < 5) {
	  System.out.println("Hello");
	  count = count + 1;
	}
        // note: at this point in the code, count stores 5
```

What does `count` remember?  The number of times we have printed out "Hello".
Are we using count correctly?
  - At the beginning, we have not printed anything and count is 0.
  - Each time we print "Hello", we add one to count.
  So, our use of count matches what we want it to do.
  What is true at the end of the loop? `count` stores 5
  We wanted to print 5 times, and count stores 5 so we did print 5 times.


## The for loop
```
	for (initial statement; condition; increment statements)
	   loop-body-statement
```
* the initial statement is a single statement
* increment statements are 0 or more statements, separated by commas (no terminating semicolons)
* condition and loop-body-statement are the same as for while loops.

### For loop behavior:
1. the initial statement is executed
2. the condition is evaluated
3. if the condition is true
  1. the loop body statement is executed
  2. the increment statements are executed
  3. repeat step 2
if the condition is false, go to the next statement of the program


__Example 4: Write a loop that prints "Hello" 5 times, but this time using a for loop.__

```
	// numHellos stores the number of times we have printed "Hello"
	for (int count = 0; count < 5; count = count + 1)
	  System.out.println("Hello");

        // note: at this point in the code count does not exist
```

Note the close similarity to the while loop we wrote.  Notice where the different statements of the while loop ended up in the for loop.

While loops and for loops in Java are really the same thing, just different formatting.

```
  for (initial-statemnet; condition; increment-statements)
    loop-body-statement
```
is the same as:
```
  {
    initial-statement;
    while (condition) {
      loop-body-statement
      increment-statements
    }
  }
```

__Note the encapsulation in a compound statement.  This means that any variables declared in the for statement will not exist once the for statement completes.__


## The do-while loop
```
   do {
      loop-body-statement(s)
   } while (condition);
```

### do-while-loop behavior:
1. Execute the loop-body-statement(s)
2. Evaluate the condition
   * If the condition is true, repeat step 1
   * If the condition is false, go to the next statement of the program

### Usage
* __The do-while loop should be avoided except in cases where you really need the body of the loop to execute before testing the condition.__
* The problem with the do-while loop is that, if you accidentally drop the "do", the rest of the code is still valid Java, but it does something entirely different! It becomes a compound statement followed by a while loop!

## When to use a while loop and when to use a for loop?
Your choice.  The two are exactly the same, just ordered differently.
* __Some guidelines:__
  * for loop advantage is that the code that describes the loop is all in the header.
    * for loops are good when the loop is controlled by variable(s) and there is a simple (or constant) increment.  
  * while loop advantage is that the syntax is simpler.
    * while loops are good when the increment is complicated
