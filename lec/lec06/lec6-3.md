# Appending elements to an array

> Create a method that takes an `int` array and an `int`.  The array is __full__, but we need to put the `int` value at the __end of the array__.

We can't change the size of an array, so instead, we __must create a brand new array__, copy the values over, and then put the new value at the end.

```
public static int[] append(int[] array, int x) {
  int[] newarray = new int[array.length + 1];

  for (int i = 0; i < array.length; i = i + 1)
    newarray[i] = array[i];

  newarray[newarray.length - 1] = x;

  return newarray;
}
```

1. Note that we needed a loop to do the copy.  If we write `newarray = array;` we only copy the address of array to `newarray` (hence, they point to the same array).

2. Note that we copied `x` in outside the loop.  We could have tried to also include x inside the loop, but that would mean an if statement and more complicated code.  To keep your code simple, __remember to have each loop accomplish one task.__

3. Note that we had to return `newarray`.  If we did not, we would lose the `newarray`.  
  * `newarray` is a local variable and so it is lost once the method ends.
  * When the method ends, all local variables and method parameters are removed from memory. If we lose newarray, we lose the address to the new array.
  * By returning the new address, we can have it outside the method.

## Usage
`myArray = append(myArray, 11);`

### How does it work?
* Creates a new array one larger than `myArray`, copy the data over, add `11` to the end, and then return the __address__ of the new array, and that address is stored in `myArray`.
* `myArray` now __points__ to a new array one larger than the array it originally pointed to.  The old array is still in memory. Java __will eventually de-allocate it if there are no other variables storing its address.__
