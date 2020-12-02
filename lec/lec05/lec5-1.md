# Fields / Variables:
There are 4 kinds of variables, and the kind you create depends on where you create it:
1. __class (static) fields:__  the variable is stored in the class (one copy that all the objects share)
	- class fields exist for the duration of the program.
        - they are given default initial values of 0, 0.0, false, or null, depending on their type
	- __null = "not a valid address"__
2. __instance (non-static) fields:__  a separate variable stored in each instance of the class.  Every instance has its own version.
	- an instance field exists as long as the containing instance exists
	- they are given default initial values of 0, 0.0, false, or null
3. __local variables:__  a variable declared inside a method.  It exists from the moment created until the end of the compound statement it is declared it.
  - they are not given an initial value, and they must be assigned a value as their first use
4. __method parameter:__ the variable(s) that store an input to the method.  It exists only in the body of the method.
  - the initial value is set by the method call input
