# 1. Social network connectivity

**Problem Description:**

Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better and use extra space proportional to n.


**Solution:**

We can add a count for the number of connected components. N members can be considered as N objects, and M timestamps can be considered as M unions. The earliest time at which all members are connected would be the time when the number of connected components equals 1.

To the WeightedQuickUnionUF class, we add a data member:
```
private int count;
```

Initially, the count would be N components.

We can update the union method as follows (adding count--):
```
public void union(int p, int q) {

	int rootp = root(p);
	int rootq = root(q);
	
	if (rootp == rootq) return;
	
	if (sz[rootp] < sz[rootq]) {

		id[rootp] = rootq;
		sz[rootq] += sz[rootp];
	} else {

		id[rootq] = rootp;
		sz[rootp] += sz[rootq];
	}

	count--; // This is important
}
```

We can check after every union if the count reaches 1 at any time.
