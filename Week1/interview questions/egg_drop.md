# 6. Egg drop

**Problem Description:**
------------------------

Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:
* Version 0: 1 egg, ≤T tosses.
* Version 1: ∼1lgn eggs and ∼1lgn tosses.
* Version 2: ∼lgT eggs and ∼2lgT tosses.
* Version 3: 2 eggs and  ∼2 * sqrt(N) tosses.
* Version 4: 2 eggs and c * sqrt(T) tosses for some fixed constant c.


**Solution:**
-------------

**Version 0** 

Toss the egg starting from the first floor, and keep moving up floors tossing the egg until it breaks. T = floor the egg first breaks. This would be of ~N complexity, as we go floor by floor.
<br></br>

**Version 1**

Toss the egg from the N/2th floor. If it breaks, repeat for the lower half of the floors. If it doesn't break, move to the upper half floors and begin by dropping from the Nth floor. This follows the binary search approach with ~logN tosses to find T.
<br></br>

**Version 2**

Drop the egg at every 2^t floor, i.e. 2, 4, 6, 8, 16, etc. If the egg breaks between floors 8 and 16, perform a binary search between these floors to find T. Both operations take ~logT time as we are constantly doubling "t" in the first case, and the second part uses binary search which is also ~logT. Therefore, this takes at a minimum ~logT time (assuming the egg breaks at any of the 2^t floors), and a maximum ~2logT (if we have to also search between 2^t and 2^(t-1) floors.
<br></br>

**Version 3**

Toss the egg from every sqrt(n) floor, until it breaks. This takes sqrt(n) tosses. By this time, only 1 egg has been used. Then, assuming the egg breaks at $c\sqrt{n}$ floor (where c is a constant), drop the egg within $c\sqrt{n}$ and $(c-1)\sqrt{n}$ floors. This will take another sqrt(n) tosses at the maximum. The maximum total complexity would then be ~2sqrt(n) tosses.
<br></br>

**Version 4**

Drop the egg with the following interval sizes - 1, 2, 3, 4, 5, etc. This means that we drop the egg at the following floors - 1, 3, 6, 10, 15, so until t.

When the egg breaks at floor T', we would've used up to t tosses. Here $T' = { t(t+1)/2}$, which for a large t, $T' \approx t^2/2$. So for $T' = floor(28)$, we would've used approximately 7 tosses. We have used up 1 egg so far. Now, we check between floors (T' - t) and T'. This would take another **t** tosses. 

So the total number of tosses would be $t + t = 2t$ tosses. Since, $T' \approx t^2/2$, we can say that $t = \sqrt{2T}$. Therefore, $2t = 2\sqrt{2T}$. This can further be simplified by taking out the constant 2, such that $2t = 2\sqrt{2}\sqrt{T}$. Taking $c = 2\sqrt{2}$, we can rewrite this as $2t = {c\sqrt{T}}$.
