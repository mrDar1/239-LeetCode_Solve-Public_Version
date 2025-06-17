import java.util.PriorityQueue;
/*problem:
given non-negative, "int[] nums"  and int "k"
return the biggest number possible after product all nums[].
can increment each element with +1 - k times.
constraint, for not get to big number for long return ans % 1_000_000_007. */

/*motivation:
each time greedly choose the smallest number and increse it with +1.
example: [1,1]
if increment 2,2 - product to 4. if increment 3,1 - product to only 3. */

/*solutions:
* 1st-using modulu at each round
* 2nd-using modulu only at end - not efficient, and fail due to overflow (or TLE) */

public class A13_MaximumProductAfterKIncrements_2233 {
    public int maximumProduct(int[] nums, int k) {
        final int MOD  = 1_000_000_007; //constraint. 1_000_000_007==1000000007!!! java ignore _ !!!
        long ans = 1; //for * must start with 1.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b); //min heap.

        for (int i : nums){
            pq.add(i);
        }

        for (int i = 0; i < k; i++) {
            pq.add(pq.remove() + 1);
        }

        while ( !pq.isEmpty()){
            ans = (ans * pq.remove()) % MOD;
        }

//        time: O3nlogn On log n - build pq,  O2k log n-add and remove +1 k times, Olog n - remoove from pq until empty.
//        space: O1
        return (int)ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A13_MaximumProductAfterKIncrements_2233_fail_at_overflow {
    public int maximumProduct(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        long ans = 1;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num: nums) {
            heap.add(num);
        }

        for (int i = 0; i < k; i++) {
            heap.add(heap.remove() + 1);
        }

        while (!heap.isEmpty()) {
            ans *= heap.remove();
        }

        return (int) (ans % MOD);
    }
}