# 2. Union-find with specific canonical element

**Problem Description:**
-----------------------

Add a method  find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.

For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.


**Solution:**
-------------
Add another integer array, `large[]`, where **large[i]** is the largest element in the component containing object i. When performing a union, we update the large array. Initially, set `large[i] = i`.

Modify union() to update large array value:

```java
public void union(int p, int q) {

	int rootp = root(p);
	int rootq = root(q);
	
	if (rootp == rootq) return;

	int largestP = large[rootp];
	int largestQ = large[rootq];

	if (sz[rootp] < sz[rootq]) {

		id[rootp] = rootq;
		sz[rootq] += sz[rootp];

		if (largestP > largestQ) {

			large[rootq] = largestP;
		}
	} else {

		id[rootq] = rootp;
		sz[rootp] += sz[rootq];

		if (largestQ > largestP) {

			large[rootp] = largestQ;
		}
	}
}
```
