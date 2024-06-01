# 5. Search in a bitonic array

**Problem Description:**
------------------------

An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.
* Standard version: Use ∼3lgn compares in the worst case.
* Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).


**Solution:**
-------------

1) Take the middle of the array
2) Compare the middle element with one of its neighbours to see if the max is on the right or the left
3) Compare the middle element with the desired value
4) If the middle element is smaller than the desired value AND the max is on the left side, then do bitonic search on the left subarray (we are sure that the value is not in the right subarray)
5) If the middle element is smaller than the desired value AND the max is on the right side, then do bitonic search on the right subarray
6) If the middle element is bigger than the desired value, then do a descending binary search on the right subarray and an ascending binary search on the left subarray.


```java
public boolean bitonic_search(int[] array, int left, int right, int value) {

	if (left == right) {

		return false;
	}

	int mid = (right + left)/2;
	if (array[mid] == value) {

		return true;
	}

	if (left + 1 == right) {

		return false;
	}

	if(array[mid] > array[mid - 1]) {

		if (value > array[mid]) {

			return bitonic_search(array, mid+ 1, right, value);
		} else {

			return ascending_binary_search(array, left, mid, value)
					|| descending_binary_search(array, mid + 1, right, value);
		}
	} else {

		if (value > array[mid]) {

			return bitonic_search(array, left, mid, value);
		} else {

			return ascending_binary_search(array, left, mid, value)
					|| descending_binary_search(array, mid + 1, right, value);
		}
	}
}

private boolean descending_binary_search(int[] array, int left, int right, int value) {

	if (left == right) {
		return false;
	}

	int mid = (right + left)/2;
	if (array[mid] == value) {
	
		return true;
	}

	if (left + 1 == right) {
		return false;
	}

	if (value < array[mid]) {

		return descending_binary_search(array, mid + 1, right, value);
	} else {

		return descending_binary_search(array, left, mid, value);
	}
}

private boolean ascending_binary_search(int[] array, int left, int right, int value) {

	if (left == right) {

		return false;
	}

	int mid = (right + left)/2;
	if (array[mid] == value) {

		return true;
	}

	if (left + 1 == right) {

		return false;
	}

	if (value > array[mid]) {

		return ascending_binary_search(array, mid + 1, right, value);
	} else {

		return ascending_binary_search(array, left, mid, value);
	}
}

public static void main() {

	int[] array = new int[]{2, 3, 5, 7, 9, 11, 13, 4, 1, 0};
	int value = 4;

	int left = 0;
	int right = N;

	// print "value found" is the desired value is in the bitonic array
	boolean found = bitonic_search(array, left, right, value);

	System.out.println(“Element exists ? - “ + (found) ? “Yes” : “No”);
}
```

