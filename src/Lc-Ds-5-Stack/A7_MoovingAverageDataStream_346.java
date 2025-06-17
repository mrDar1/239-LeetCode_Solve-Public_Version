/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/* problem (very unclear... so here is long version) :
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

so here is how to read this question:
init an object, with a function "next" which gets a value and return the average of all last "window size"
# previuse nembers given to "next" before.

for example, let 3 be the window size.
calling next(1) will return the avg of 1 which is 1
calling next(3) will return (3+1)/2
calling next(1) will return (1+3+1)/3
calling next(2) will return (2+1+3)/3 ** we dropped the first 1 as we are limited to 3 values of "window size"*/


/* 3 solutions (each time uncommnet another):
 1st - using queue
 2nd - same as 1, small differences
 3rd - circular queue with array - very nice and easy implement! */

public class A7_MoovingAverageDataStream_346 {
    public static void main(String[] args){
        int[] arr1 = {3,1,10,3,5}; // desire output: null, 1, 5.5, 4.667, 6 - it pass at LC - my testing arent good.
        int size = 3; // the first element of arr is the window size!!!

        Solution_MoovingAverageDataStream_346 obj_346 = new Solution_MoovingAverageDataStream_346(size);

        for(int val : arr1){
            double param_1 = obj_346.next(val);
            System.out.println(param_1);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_MoovingAverageDataStream_346 {

////    my second good try: using Queue
//    int size;
//    int cursum = 0;
//    Queue<Integer> window = new ArrayDeque<>();
//
//    public Solution_MoovingAverageDataStream_346(int size){
//        this.size = size;
//    }
//
//    public double next(int val) {
//        window.add(val);
//        cursum += val;
//
//        if (window.size() > size){ //usually at slidind window need loop, here constraint - each next only 1 val, so "if" is enough
//            cursum -= window.poll();
//        }
////        time: O1 - notice each time we give this method only 1 obj!
////        spcae: Ok - k==size of window.
//        return (double) cursum / window.size();
//    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    int size;
//    int cursum = 0;
//    Deque<Integer> window = new ArrayDeque<>();
//
//    public Solution_MoovingAverageDataStream_346(int size) {
//        this.size = size;
//    }
//
//    public double next(int val) {
//        window.add(val);
//        int first = window.size() > size ? (int)window.poll() : 0;
//
//        cursum += val - first;
//
////        time: O1 - notice each time we give this method only 1 obj!
////        space: Ok - k==size of window.
//        return (double)cursum / window.size();
//    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    // using circular Queue with array
    int size;      //size of "circular Queue"
    int[] queue;   // our "circular Queue"
    int windowSum = 0;
    int head_ptr = 0;  //ptr to cur location at circular Queue ("tail" will show who to erase, always the next element after head)
    int count = 0; // count number of elements seen so far. we need "count" for if we have window_size - that is bigger than received elements.

    public Solution_MoovingAverageDataStream_346(int size) {
        this.size = size;
        queue = new int[size];
    }

    public double next(int val) {
        ++count;

        // find the tail index of the window (index of element to erase at cur round)
        int tail_ptr = (head_ptr + 1) % size;
        windowSum = windowSum - queue[tail_ptr] + val; //subtract old element and add the new val.

        // move head ptr 1 step forward - but in circular manner
        head_ptr = (head_ptr + 1) % size;

        queue[head_ptr] = val; //update "circular Queue" with new val.

        return (double)windowSum / Math.min(size, count);
        ////        time: O1 - notice each time we give this method only 1 obj!
        ////        space: Ok - k==size of circular queue.
    }
}









/* my first nice fail try:
class Solution_MoovingAverageDataStream_346 {
    int size;
    int cursum = 0; // current window sum. allways start at 0, whenever size be diffrent - as what we enter.
    Deque<Integer> window = new ArrayDeque<>();
    public Solution_MoovingAverageDataStream_346(int size) {
        this.size = size;
    }
    public double next(int val) {
        window.add(val);
        cursum += val;

        if (window.size() >= size){
            cursum -= window.peek();
            window.removeFirst();
        }

        return 1.0* cursum / window.size();
    }
}
*/

