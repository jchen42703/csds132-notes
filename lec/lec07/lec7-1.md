# Multidimensional Arrays
Java does not have multi-dimensional arrays. Instead, Java allows arrays to store other arrays.

For example, to declare and create an array storing double:
`double[] array = new double[10];`
To declare and create an array of arrays that stores double:
`double[][] table = new double[5][8];`

We read the type right to left.  The right most `[]` indicates that we have an array, and what comes before the `[]` (i.e. `double[]`) indicates the type stored in the array. We read the array creation left to right.  What we have is an array of 5 elements, and each element is an array of 8 doubles.

To access an element:

`table[3][5]`   <-  again we read left to right.  In the first array, we go to the 4th element.  That element is an array of double.  (We can think of it as the 4th row of the table.) Then, we go to the 6th element of that array.  (We can think of it as going to the 6th column of the array.)

But if thinking of the array as rows and columns, always remember that there is no "column" stored in memory.  __We just have an array storing arrays.__

Since it is an array storing arrays, __we can do things in Java that you can't do with a "real" multidimensional array.__

```
int[][] a = new int[10][5];     // creates an array of 10 "rows", each row is an array of 5 ints
a[3] = new int[100];		// now the 4th "row" is length 100 (but the others all still have length 5
a[4] = a[3]			// the 5th row is also the 4th row
a[0] = null;			// the 1st "row" is now gone
```

__Here are some things we did not get to in lecture, but I add them here for completeness.__
1) You do not have to specify all the sizes of the array as long as what you declare can be a valid array
2) Looping through a multidimensional array is the same as looping through a normal array

We can have an array store arrays of array:
```
String[][][] biggerArray = new String[5][10][20];
 Note that we do not have to specify all the sizes.  
String[][][] biggerArray
String[][][] biggerArray = new String[5][][];
String[][][] biggerArray = new String[5][10][];
```

are all legal.  __When we leave a size blank, Java does not create those arrays.__  For example, new String[5][][] creates an array of 5 variables, each variable has type String[][], but the variables are
set to null.

Example: loop through the elements of a two dimensional array
```
int[][] array = new array[10][3];

for (int i = 0; i < array.length; i++) {	<-  notice that nothing has changed from how we traverse through a "normal" array

for (int j = 0; j < array[i].length; j++) { 	<-  nothing changed here, except that the array we are going to traverse through has its address stored in table[i]

```
