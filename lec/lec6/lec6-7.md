# Loop Testing

## How to test loops?
Here is a good "rule of thumb" to follow to help you catch most of the usual bugs that can show up in loops:
1. Test 0, test 1, test many
2. Test first, test middle, test last

## Example with the `binarySearch` method
Consider the `binarySearch` method.
* What does `"test 0, tests 1, test many"` mean?
  * One interpretation is test arrays of length 0, 1, and many.
* What does `"test first, test middle, test last"` mean?
  * One interpretation is to test where we are searching for an element in at the front of the array, an element at the back, and an element somewhere in the middle.
  * Another interpretation is to test where we find the element quickly (the first iteration of the loop), and one where the loop runs to the end (the element is not in the array).

So, this guide suggests that we have to test the following array lengths:  0, 1, and large, and we have to search for the middle element, elements not the middle, the element at the front, the element at the end, and elements that are not in the array: one smaller than all elements in the array, one larger than all elements in the array, and one that falls between the largest and smallest.
