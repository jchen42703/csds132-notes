# Arrays and Typecasting

## `Array` to `Object`
We can always typecast an array to `Object` because everything that is not a primitive value in Java is an object.

```
  int[] array1     <- array1 will store the location of the array in memory
  new int[100]     <- the new operator will return the address of the array
  (Object)array1   <- the typecast is legal because Object is above everything in the class hierarchy.
```

## Typecasting the individual variables in the array
Because a typecast on a non-primitive type does not change the true type of the object, just its current type, Java __allows us to typecast the types of the variables for arrays of non-primitive types:__

```
JFrame[] frameArray = new JFrame[100]
Object[] oArray = (Object[])frameArray; <- Legal.  Object is wider than JFrame.  An explicit typecast is not needed.

frameArray = (JFrame[])oArray;		<- Legal as long as the true type of oArray is an array storing JFrames or something below JFrame in the hierarchy

oArray[0] = new JFrame();
oArray[1] = "Hello"			<- Illegal.  While Object o = "Hello" is legal, here oArray[1] is referring to an element of frameArray.  frameArray knows that it is only to store JFrames.
```

This does not work for primitive arrays:
```
int[] array = new int[10];
double[] darray = (double[])array;	<- Illegal.  Even though double is wider than int, Java will not allow this.
char[] letters = new char[100];
short[] values = (short[])letters;	<- Illegal.  Even though char and short are the same byte size, Java will not allow this.
```
Java forbids any typecasting between primitive array.
