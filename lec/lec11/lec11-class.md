# Exception Handling

Consider the following class:
```
	public class Averager {

	  // return the average of the numbers (represented as strings) in the array
	  public static int average(String[] values) {
	    int total = 0;
	    for (int i = 0; i < values.length; i++) {
	      total += Integer.parseInt(values[i]);
	    }
	    return total / args.length;
	  }

	  public static void main(String[] args) {
	    int average = average(args);
	    System.out.println("The integer average of the inputs is " + average);
	  }
	}
```
Since we have a main method, this program can be run stand alone, and it averages (using int average) the command line arguments
```
	java Averager 4 5 6
	> 5
```
## What can go wrong?
1.  If the user does not enter an `int` (for example 4.5 or apple).  In this case, the program will generate a `NumberFormatException`.
2.  If the user does not enter anything.  Then `args.length` is a 0, and the program will generate an `ArithmeticException`.

## How to deal with errors:
1. Print an error message.  This solution should only be used in routines that are directly interacting with the user.  Otherwise the error message will be ignored.
2. Return a special "error" value.  This solution should only be used when either the error value makes sense or there is no possibility that the error value could be confused for legitimate output.
3. Use a separate channel to send an error indication.
  * For this technique, Java uses __exceptions.__
4. Deal with the error.

## Unchecked Exceptions
__We have already seen several types of exceptions:__
`NumberFormatException`, `ArithmeticException`, `NullPointerException`, `IndexOutOfBoundsException`
* Each of these exceptions are called __unchecked exceptions.__  All unchecked exceptions are subclasses of either `Error` or `RunTimeException`.
* With unchecked exceptions, the __programmer does not have to explicitly state what to do if an exception occurs (is thrown).__
  * The default is to stop the method and throw the exception on the the calling method.

## Checked Exceptions
The other types of exceptions (for example `IOException`), are checked exceptions.  __With checked exceptions, the programmer must specify what to do if the exception is thrown.__
  * It is a compiler error to use code that can generate a checked exception but not explicitly state how to deal with it.
* __Key point:__  exceptions are just objects and they otherwise behave exactly like all other objects in Java.
  * __Exceptions are not errors.__  Throwing an exception is just another way (besides the return statement) of getting data out of a method.
  * However, __throwing an exception is like "break" in a loop.__  Not only does it stop the method execution, but the calling method might simply throw exceptions to the methods that call it.
  * As a result, exceptions are harder to reason about logically than "normal" return, and __overuse will make our code more difficult to manage and harder to determine correctness.__
  * __Exceptions should be used for handling infrequent situations, and thus are often used for errors.__

## How to Handle an Exception
There are two things the programmer can do for an exception: handle the exception or throw the exception on to the calling method.

To throw the exception, we can do either or both of these:
1. Place `throws ExceptionType` in the header of the method.  See the lab example with `IOException`.  This must be done if throwing a checked exception.
  * This notation in the header both informs the compiler that the specified exception may be thrown and sets the default behavior of the method to throw the exception should it occur.
2. Explicitly throw the exception with the `throw` statement.
```
	throw e;
	throw new ExceptionType();
```
To handle the exception, we use the try/catch statement.
```
	try {
	  - code that could throw an exception
	}
	catch (ExceptionType e) {
	  - code that is executed if an exception of type ExceptionType occurs inside the try block
	  - e is a variable that stores the exception object address, and it exists inside this block
	}
	finally {
	  - code that is always executed upon exit of the try and catch blocks
	}
```
There may be 0 or more catch blocks with a try and at most one finally block.  There must be at least one catch block or a finally block with the try statement. Note that the catch statement is a variable declaration. The variable `e` will hold the __address of the exception that was thrown in the try block code.__

## Example:  Dealing with the integer divide by 0

The divide by 0 occurs in the line: `return total / values.length;`

We can place a try/catch block around it and catch `ArithmeticExceptions`:
```
  try {
    return total / values.length;
  }
  catch (ArithmeticException e) {
    // do something?
  }
```

In class, we decided to print an error message, and in this case, the error should be handled in main, and not in average.

Exceptions have the benefit that we can handle the exception where it makes the most sense, not necessarily where they occur.

So, we do not place the try block around "return total / values.length;" but instead place it around the code in main that calls average.
```
	  public static void main(String[] args) {
	    try {
	      int average = average(args);
	      System.out.println("The integer average of the inputs is " + average);
            }
            catch (ArithmeticException e) {
	      System.out.println("You entered nothing to average.");
	    }
	  }
```

## Example 2: Dealing with the `NumberFormatException`

If the input contains a String that does not represent a number, a `NumberFormatException` occurs.

We catch the exception and try formatting the input as a double.  (Note we could have just formatted it as a double to begin with, but then we would not have as much fun dealing with errors.)

Then, we need a second try/catch block inside the catch block in case the number is also not a double.

We can use more than one catch statement with a try:
```
	try {
	}
	catch (ExceptionType1 e) {
	}
	catch (ExceptionType2 e) {
	}
```
* On an exception, Java will run through each type from the top to the bottom and stop at the first one that matches.  
* It is important that if one of the exception types is the parent of the other, __the child type must come first.__
  * Otherwise both parent and child types will match the first parent type declaration, and the catch block of the second child type declaration will not be executed.
* Another important point is that this will catch multiple exceptions that occur in the try block. An exception that occurs inside the first catch block is not caught by the second catch. Anything caught must be inside a try, and so to catch the error that occured inside the catch, we needed to nest another try/catch inside the catch block.

## One last example: `BadDataException`
* We added one more example in that we created our own exception so that we can both report a result and indicate an error condition.
* By creating our own exception, we could store the proper return value in the exception and then throw the exception.
The calling method both receives the exception so that it knows that an error happened, and it can retrieve the needed value from the exception.
