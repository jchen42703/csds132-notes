# Conditional statements

## What are conditionals?
```
if (condition)
   then-statement
else
   else-statement
```

condition is an expression with a boolean type then-statement and else-statement can be either simple or compound (but they must be a single statement)
__The "else else-statement" part is optional.__

## How it works
1. the condition is evaluated
2. if the condition is true, the then-statement is executed
3. if the condition is false and the else-statement exists, the else-statement is executed
4. the next statement of the program is executed

## Proper Branching v. Nested Branching
```
Scanner in = new Scanner(System.in);
int grade = Integer.parseInt(in.nextLine());
if (grade >= 90) {
  System.out.println("You got an A!");
} else if (grade >= 80) {
  System.out.println("You got a B!");
} else if (grade >= 70) {
  System.out.println("You got a C!");
} else if (grade >= 60) {
  System.out.println("You got a D!");
} else {
  System.out.println("You got an F!");
}
```
is the same as:
```
Scanner in = new Scanner(System.in);
int grade = Integer.parseInt(in.nextLine());
if (grade >= 90) {
  System.out.println("You got an A!");
} else {
  if (grade >= 80) {
    System.out.println("You got a B!");
  } else {
    if (grade >= 70) {
      System.out.println("You got a C!");
    } else {
      if (grade >= 60) {
        System.out.println("You got a D!");
      } else {
        System.out.println("You got an F!");
      }
    }
  }
}
```
---
Attempt 1:  A year is a leap year if is is divisible by 4 but not divisible by 100 unless it is divisible by 400

```
	public boolean leapYear(int year) {
	  if (year % 4 == 0) {
	    if (year % 100 == 0) {
	      if (year % 400 == 0)
	        return true;
	      else
	        return false;
            }
            else
              return true;
	  }
	  else
	    return false;
	}
```

Attempt 2:  The same but now we can keep track of the cases that are "removed" to make it easier to see the logic

```
	public boolean leapYear(int year) {
	  if (year % 4 != 0)           // take care of the case where we know the value right away : when year is not divisible by 4
            return false;
          else {                       // from here on we know that year IS divisible by 4
	    if (year % 100 != 0)       // this also removes a case where we know the answer
	      return true;
            else {		       // from here on, year IS divisible by 4 AND is divisible by 100
	      if (year % 400 != 0)
	        return false;
	      else
	        return true;
            }
	  }
	}
```

Attempt 3: Notice above that the "else" statement is a single conditional statement, even if it takes more than one line.
In this case, we can not only remove the { }, but we can move the "if" up to be on the same line as the "else".
That formatting looks a little better and is similar to languages that have the "elseif" feature. Java does not have an "elseif", but with our formatting, we get something just as nice:

```
	public boolean leapYear(int year) {
	  if (year % 4 != 0)
            return false;
          else if (year % 100 != 0)
	    return true;
          else if (year % 400 != 0)
	    return false;
	  else
	    return true;
	}
```

Attempt 4: The same, but without an if statement.  Sometimes the if statement is not even needed!

```
	public boolean leapYear(int year) {
	  return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	}
```
