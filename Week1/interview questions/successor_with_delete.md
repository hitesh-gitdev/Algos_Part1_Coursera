# 3. Successor with delete

**Problem Description:**
------------------------

Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
* Remove x from S
* Find the successor of x: the smallest y in S such that y≥x.

design a data type so that all operations (except construction)  take logarithmic time or better in the worst case.


**Solution:**
--------------

When a number is removed, union the immediate neighbour if it is also removed. The largest successor would then be equal to the largest number in that component plus 1. Here, we use the datatype created in question 2, where the find() method returns the largest element of the connected component.

```java
public class SuccessorWithDelete {

	private boolean data[];

	// To find largest unremoved element
	private UFWithFindLargest uf;
	private int N;

	public SuccessorWithDelete(int N) {

		this.N = N;
		data = new boolean[N];
		for (int i = 0; i < N; ++i)
		data[i] = true;
		uf = new UFWithFindLargest(N);
	}

	public void remove(int x) {

		data[x] = false;

		if (x > 0 && !data[x-1]) {

			uf.union(x, x-1);
		}

		if (x < N - 1 && !data[x+1]) {

			uf.union(x, x+1);
		}
	}

	public int successor(int x) {

		if (data[x]) {

			return x;
		} else {

			int res = uf.find(x) + 1;
			if (res >= N) {

				StdOut.println("No successor can be found");
				return -1;
			} else {

				return res;
			}
		}
	}
}
```
