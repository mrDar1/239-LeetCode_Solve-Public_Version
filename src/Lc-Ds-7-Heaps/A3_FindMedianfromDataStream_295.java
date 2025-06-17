import java.util.Comparator;
import java.util.PriorityQueue;

/*problem:
The median is the middle value in an ordered integer list.
If the size of the list is odd, the median is the middle.
If the size of the list is even, the median is the average of the two middle values.

//note: we dont recive given int[] - as it would be easy, we need to find median inside dataset that is continuously added to.

Implement the MedianFinder class:
MedianFinder() initializes the MedianFinder object.                 //constructor - i didnt use it.
void addNum(int num) adds the integer num to the data structure.    //each time add 1 new element.
double findMedian() returns the median of all elements so far.      //return median. constraint-in O1.
*/


/*motivation:
* use 2 heaps - max-heap and min-heap,
* both should be with same number of elements.
* when one has more element than the other - its odd input and the extra element is the median - simple poll().
* when both equal - poll() from both - and the avg of them is the median.
*
* how to sort 2 heaps:
* max-heap hold all MIN values
* min-heap hold all MAX values
* how come? since its easy to take out max values from max-heap, and so remain with only small-values.
*
* to ensure each element at it right heap - traverse each element through both - and so ensure both heaps "sorted" in heap manner.
*
*
* some extra explain:
* to traverse each element through both - we actually need 3 offer and poll!!
* first time add to max (randomly selected could be min also),
* add to min what pop from max - at that point max-heap sorted but min-heap may still have unsorted elements!
* so again need to add to max what pop from min - now both heaps "sorted" in heap manner.
* note - if both are same size - dont need the last pop!! (=sort min-heap), how come?
* - as we anyway poll from both and compare! so will happen anyway! */

public class A3_FindMedianfromDataStream_295 {
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>((a,b) -> b - a);
//        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder()); // can also be done by this line, slower run time...

    public void addNum(int num) {
        max.add(num); //chose to start with max arbitrary.
        min.add(max.remove());

        if (min.size() > max.size()){
            max.add(min.remove());
        }
    }

    public double findMedian() {
        if (max.size() > min.size()){
            return max.peek();
        }
        return ((max.peek() + min.peek()) / 2.0);
    }
//    time: add- O3(log n) add and removal.
//    time: find-O1 - to find.
//    space:O1n - to store int[].
}
