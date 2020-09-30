# Loops and Arrays
How do we fill an array? Usually with a loop.
```
JFrame[] frames;           // frames will store (the location to) an array that stores JFrames
frames = new JFrame[30];   // now frames has the address of a chunk of memory divided into 30 variables of type JFrame.  But no JFrame is yet stored in frames.  To place a separate JFrame in each one:

for (int index = 0; index < frames.length; index = index + 1) {
  frames[index] = new JFrame();
}
```

If you have a small array, then you don't need a loop.  You can enter the elements on at a time, or you can use an __"array constructor"__ shortcut.  See the textbook reading for more.

## Array constructor
```
// looks like this:
new Array(element0, element1[, ...[, elementN]])
new Array(arrayLength)
```
For example,
```
// Single parameter
let fruits = new Array(2);

console.log(fruits.length); // 2
console.log(fruits[0]);     // undefined

// Multiple parameters
let fruits = new Array('Apple', 'Banana');

console.log(fruits.length); // 2
console.log(fruits[0]);     // "Apple"
```

### Literal Notation
```
let fruits = ['Apple', 'Banana'];

console.log(fruits.length); // 2
console.log(fruits[0]);     // "Apple"
```

## Reversing an Array
* Create a method that __reverses the contents of an array.__
  * Each slot in the array is just a variable and so we will use variable assignment. We will need one extra variable to store values so we do not lose any values.

1. Save the value in the back of the array,  `array[array.length - 1 - index]`
2. Store the __value at the front__ of the array array[index] __into the back__ of the array `array[array.length - 1 - index]`
     - this __overwrites the__  value in `array[array.length - 1 - index]`.  Good thing we saved it!
3. Store the saved value into `array[index]`.

When do we stop the loop?  You might be tempted to say when index reaches the end (`array.length`), but that is not correct.  Can you see why?
* Because `array.length` out of the bounds of the array indices.

```
	public static void reverse(int[] array) {
	  for (int index = 0; index < length / 2; index = index + 1) {
	    int save = array[array.length - 1 - index];
	    array[array.length - 1 - index] = array[index];
	    array[index] = save;
	  }
	}
```
