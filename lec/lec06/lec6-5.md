# Linear Searching in an Array

Here is a standard loop for searching for an item in an array:

```
	public static int linearSearch(int x, int[] array) {
	  for (int i = 0; i < array.length; i++) {
	    if (x == array[i])
	      return i;
	  } 			// at this point i = array.length so we checked all entries of array
	  return -1;
	}
```

What is the subgoal of the loop? It is what must be true after each iteration for the loop to continue:  "x is not in the first i elements of array"


## Is this the best search method we can write?

In the worst case, how many array locations does it inspect?
* __All of them.__ So in the worst case, the time the method takes will be proportional to the length of the array.
  * On average, it will need to check half the elements in the array.  So in the average case the time the method takes is also proportional to the length of the array.

## Can we write a faster loop?
No!  What is the proof?  No matter how we decide to run through the array, until we look at every location, __we cannot be sure if x is not in the array.__

So, unless we know more information about the array, our linearSearch method is optimal.


## Searching in a sorted array

Suppose the array is sorted in non-decreasing order.  Now can we write a faster method?

### Proposed Algorithm (Binary Search)
 1. Examine the middle element.
 2. If the middle element is smaller than x, repeat on the upper half of the array.
 3. If the middle element is larger than x, repeat on the bottom half of the array.

This algorithm looks more complicated to code, but __is it fundamentally faster?__

We start with a list of n elements, and __each time, we cut the number of elements we are considering in half.__

> n -> n/2 -> n/4 -> n/8 -> ... -> 1

So, the number of iterations (and the number of elements of the array we check) is `k` where `n / (2^k)) = 1.`
`k = log(base 2) n`

Therefore, it's a big improvement over the first method (for sorted arrays).
