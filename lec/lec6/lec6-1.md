# Arrays

* An array is a collection of variables of the same type, stored in a contiguous chunk of memory.

## Creating Arrays
We can create an array to hold variables of any type:
```
int[] array1      - array1 is an array that will store ints
JFrame[] array2   - array2 is an array that will store JFrames
double[] array3   - array3 is an array that will store doubles
double[][] array4 - array4 is an array that will store double[].  That is, it is an array that stores arrays that store doubles.
```

We can initialize the array using the new operator and stating how many variables (elements) will be in the array:

* `array1 = new int[100];`
  * array1 now stores the address for an array that contains 100 int variables.

* `array2 = new JFrame[30];`
  * array2 now stores the address for an array that contains 30 JFrame variables.

* Now, to access each element, we again use the square brackets.  __To store a value in the first element (variable) of the array:__ `array1[0] = 12;`
  * Remember, the we start counting from 0 in Java!

Each element of an array is a variable of the given type.  So all the rules of variables apply:
```
array1[2] = 3.15   <- Illegal!  We are trying to store a double in a variable of type int
array1[2] = 7      <- Legal!
array1[2] = 'c'    <- Legal!  char is narrower than int so Java will automatically widen the 'c' to int
array1[2] = array3[2]   <-  Illegal!  We are trying to store the value from a double variable into an int variable
array1[2] = (int)array3[2]  <-  Now it is legal because of the typecast.
```

## Array Disadvantage
Because the array is stored in contiguous memory, we __can not change the length (number of variables)__ of the array once it is created.
  * _Contiguous memory allocation is a classical memory allocation model. Here, a system assigns consecutive memory blocks (that is, memory blocks having consecutive addresses) to a process._

## Array Advantage
Because the array is stored in contiguous memory, we can access each element in a single step, no matter how large:
`int[] a = new int[1000000];`
* For example, to access `a[95436]`, we __do not need to run through the array to the 95436'th entry.__
* We know the address of `a` (it is stored in the variable a), we know the byte size of the type of the array (for example, each int is 4 bytes) and we know which one we want (index 95436).
So the `address of a[95436] is:  (address of a) + 4 * 95436`
