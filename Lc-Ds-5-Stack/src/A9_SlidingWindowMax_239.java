import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
/*problem:
* use a sliding window size 'k' - for each window return max element value.*/

/*solutions:
* 1st - brute force (work but TLE).
* 2nd - use decreasing monotonic Dequeue*/

public class A9_SlidingWindowMax_239 {
    public static void main(String[] args)
    {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k = 3;
//        expected Output: [3,3,5,5,6,7]

        Solution_SlidingWindowMax_239 obj_239 = new Solution_SlidingWindowMax_239();
        System.out.println( Arrays.toString(obj_239.maxSlidingWindow(arr,k)) );
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//1st:
class Solution_SlidingWindowMax_239_brute_force {
    public int[] maxSlidingWindow(int[] nums, int k){
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length - k + 1 ; i++) {
            ans[i] = findMaxOutOfK(nums, i , k);
        }

        return ans;
    }

    private int findMaxOutOfK(int[] nums, int start_index, int k) {
        int curmax = Integer.MIN_VALUE;
        
        for (int i = start_index; i < start_index + k; i++) {
            curmax = Math.max(curmax, nums[i]);
        }
        
        return curmax;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd:
class Solution_SlidingWindowMax_239 {
    /*psudo:
    * we use decreasing monotonic Dequeue: each time receive new element,
    * store in decreasing monotonic Dequeue order.
    * put in result the first of the stack
    *
    * note: must use Dequeue! when new element bigger than our "stack" pop from last.
    * but, whenever reach size of window - poll from first.
    * also, whenever put to "ans" - we peek at first from "stack" (it is the biggest element) */
    public int[] maxSlidingWindow(int[] nums, int k)
    {
        int[] ans = new int[nums.length - k + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // < hold indexes not values! >

        for (int i = 0; i < nums.length; i++) {
            // maintain monotonic decreasing.
            // all elements in the deque smaller than the current one
            // have no chance of being the maximum, so get rid of them
            while ( !queue.isEmpty() && nums[i] > nums[queue.getLast()] ) {
                queue.removeLast();
            }

            queue.addLast(i);

            // when reach window size - remove the first-element:
            // queue[0] is the index of the maximum element.
            // if queue[0] + k == i, then it is outside the window
            if (queue.getFirst() + k == i) {
                queue.removeFirst();
            }

            // only add to the answer once our window has reached size k
            if (i >= k - 1) {
                ans[i - k + 1] = nums[queue.getFirst()];
            }
        }

        //time: O1n (size of nums[])
        // space: Ok - given size of window, (Q cant grow larger than window size - k element).
        return ans;
    }
}