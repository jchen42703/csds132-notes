# The Java Memory Model, part 2

The memory is divided into a heap and a stack.

* The heap is used to store classes and instances.
* The stack is used to store __information used by method calls.__

## Stack Frame (a.k.a Scope)
Everytime a method is called, a __"stack frame"__ is placed on the top of the stack.  When the method __exits__, that frame is taken off the top of the stack.
__The top of the stack always contains the stack frame of the currently executing method.__

__The stack frame contains:__
1. Method parameters (including the "hidden" parameter this for non-static methods (`this`))
2. Any local variables declared inside the method
3. Bookkeeping needed to return from a function (where in the code do we need to start executing, and what should we do with the value returned by the method?)

## Compound statements (blocks of code)
When a compound statement containing a variable declaration is encountered, a __"mini-frame" is placed on the stack that contains any variable declared inside the compound statement.__  This "mini-frame" is removed once the compound statement is done executing.

__Example 1:__  We did this example that is similar to the lab.
```
public boolean earnsMoreThan(Employee e) {
  return this.getSalary() > e.getSalary();
}
```

Suppose we run `e1.earnsMoreThan(e2)`.

On the stack will go a stack frame for `earnsMoreThan.` (I will call this Frame 1). The stack frame contains space for 2 parameters: `this` (because the method is not static) and `e`.
* `this` gets assigned the address that is stored in `e1`.
* `turnDisplayOn` gets assigned the address stored in `e2`.

__Now the method runs.__

First the method `this.getSalary()` is called. On the stack will go a stack frame for `getSalary`. (I will call this Frame 2).
__Frame 2 sits on top of Frame 1.__  The stack frame contains space for one parameter, `this`. `this` will store the same address as is in this on Frame 1.  

Now the method `getSalary` runs.  It will use the this in the top frame of the stack (it stores the same address as in this of Frame 1 and that is the same address as in e1).
When it is done executing, its frame, __Frame 2, is removed from the stack.__ The top of the stack is now Frame 1. The execution returns to `earnsMoreThan` and `e.getSalary()` is called.

On the stack will go a stack frame for `getSalary.` (I will call this Frame 3). __Frame 3 sits on top of Frame 1.__  The stack frame contains space for one parameter, `this`. `this` will store the same address as is in e on Frame 1.  

Now the method `getSalary` runs. It will use the `this` in the top frame of the stack (it stores the same address as in e of Frame 1 and that is the same address as in e2).
When it is done executing, its frame, Frame 3, is removed from the stack. __The top of the stack is now Frame 1.__
The execution returns to `earnsMoreThan`, the two values returned by `getSalary` calls are compared, and that value is returned. __And Frame 1 is now removed from the stack.__


## TAKE AWAY RULE
A __local variable__ exists from the moment it is declared (that is when it is placed into its frame, method frame or mini-frame, __until the end of the compound statement it is in (that is when the frame it is in gets removed from the stack).__


### Example 2:
Computing the factorial of an integer. 5! = 5 x 4 x 3 x 2 x 1

We will use a technique called __recursion.__  In recursion, we identify a base case (the smallest case for which the method needs to work), and we handle the base case explicitly.

For other cases, we reduce the problem to bring it closer to the base case, and call the method on the reduced problem.

__What is the smallest case?  0! = 1  (by mathematical definition)__ If we assume that we can correctly compute factorial for smaller values, how do we compute n! ?  We note that n! = n x (n-1)!
```
public static int factorial(int n) {
  if (n == 0)
    return 1;
  else
    return factorial(n - 1) * n;
}
```

* Now, we trace the behavior of calling `factorial(6)`.
The stack places a new method frame for `factorial` on the stack.
  1. bookkeeping info
  2. the parameter n storing the value 6  (note there is no hidden parameter this because factorial is a static method)
* In the execution of `factorial(6)`, we get to the line `return factorial(n-1) * n`.  This is a method call, so we must place a frame on the stack.
* A new frame for `factorial(5)` is placed on the stack on top of the frame for `factorial(6)`.  The frame contains:
  1. bookkeeping info
  2. the parameter variable n storing the value 5
  * NOTE: The parameter variable `n` in the frame of `factorial(5)` is a __DIFFERENT__ variable from the parameter variable `n` of the frame for `factorial(6)`. They both have the same name, but the __memory location__ (location on the stack) is different, and they each are storing a different value.
* In the execution of `factorial(5)`, we get to the line `return factorial(n-1) * n`.  This is a method call, so we must place a frame on the stack.
* A new frame for `factorial(4)` is placed on the stack on top of the frame for `factorial(5)`.  The frame contains:
  1. bookkeeping info
  2. the parameter variable n storing the value 4

__And the process continues.__

* Finally, we get to the method call `factorial(0)`.  __At this point we have 5 frames on the stack.__  At the top is the frame `for factorial(0)`, under it is the frame for `factorial(1)`, etc, down to the bottom which is the frame for `factorial(4)`.  When `factorial(0)` returns, the value 1 is sent to the line `return factorial(n-1) * n` and the frame for `factorial(0)` is removed.  Now the top frame of the stack is the frame for `factorial(1)`.  So, when the line `return factorial(n-1) * n` is executed, the return value of 1 is used, and the value of the parameter variable `n` is 1.
* At this time, we multiply `1*1` and the method returns 1. When the `factorial` method returns, the top frame is removed from the stack. The frame underneath is now the top frame. That frame has a variable n storing the value 2. So, when the line `return factorial(n-1) * n` is executed, `n` stores 2, the value returned was 1, and the
multiplication is 2*1.  Thus, the value of 2 is returned.
* When the `factorial` method returns, the top frame is removed from the stack.  The frame underneath is now the top frame. That frame has a variable n storing the value 3. So, when the line `return factorial(n-1) * n` is executed, `n` stores 3, the value returned was 2, and the
multiplication is 3*2.  Thus, the value of 6 is returned.
* When the `factorial` method returns, the top frame is removed from the stack.  The frame underneath is now the top frame.
* That frame has a variable `n` storing the value 4.  So, when the line "return factorial(n-1) * n" is executed, n stores 4, the value returned was 6, and the
multiplication is 4*6.  Thus, the value of 24 is returned.
* When the `factorial` method returns, the top frame is removed from the stack.  The frame underneath is now the top frame. That frame has a variable `n` storing the value 5.  So, when the line `return factorial(n-1) * n` is executed, `n` stores 5, the value returned was 24, and the multiplication is 5*24.  Thus, the value of 120 is returned.
* When the `factorial` method returns, the top frame is removed from the stack.  The frame underneath is now the top frame. That frame has a variable `n` storing the value 6.  So, when the line `return factorial(n-1) * n` is executed, `n` stores 6, the value returned was 120, and the
multiplication is 6*120.  Thus, the value of 720 is returned.

Finally, our call to `factorial(6)` returns the value 720.
