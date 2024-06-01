# 4. 3-SUM in quadratic time

**Problem Description:**
------------------------

Design an algorithm for the 3-SUM problem that takes time proportional to n^2 in the worst case. You may assume that you can sort the n integers in time proportional to n^2 or better.


**Solution:**
-------------

So there are two ways of solving this:
1) This uses a hashmap, and hence, has increased space complexity.
2) The other approach uses three pointers and only uses additional space for storing the triplets.

We can preprocess the input, by sorting it with an ~NlogN sorting algorithm (like heapsort, quicksort, mergesort etc). Also, we can encounter duplicates in the array. Since removing duplicates from an array can have ~N^2 complexity, we can ignore the duplicates while processing the array for triplets instead.

The second approach is better, as it has lower space complexity.

```java
public List<List<Integer>> threeSum(int[] nums) {

	Arrays.sort(nums);
	List<List<Integer>> res = new ArrayList<>();

	// values over 0 cannot sum to zero, so we can stop i at 0
	for (int i = 0; i < nums.length && nums[i] <= 0; i++) {

		// This is to address duplicates
		if (i == 0 || nums[i - 1] != nums[i]) {

			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {

				int sum = nums[lo] + nums[hi] + nums[i];
				if (sum < 0) {

					++lo;
				} else if (sum > 0) {

					--hi;
				} else {

					// store triplet, decrement hi and increment lo
					res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));

					// skip duplicates
					while (lo < hi && nums[lo] == nums[lo - 1]) {

						lo++;
					}

					while (lo < hi && nums[hi] == nums[hi + 1]) {

						hi--;
					}
				}
			}
		}
	}

	return res;
}
```
