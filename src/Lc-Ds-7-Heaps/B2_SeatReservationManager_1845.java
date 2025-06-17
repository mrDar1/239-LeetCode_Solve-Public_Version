import java.util.PriorityQueue;
/*problem:
Design a system that manages the reservation state of n seats that are numbered from 1 to n:
constraint: first give the low-index seats only then the big one.
* class SeatManager {
    public SeatManager(int n) {     //constructor - how many seats with have in our "theatre"
    }
    public int reserve() {          //Fetches smallest-numbered unreserved seat, reserves it, and returns its number.
    }
    public void unreserve(int seatNumber) { //free seat with given number.
    }}*/
/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
/*solutions:
* 1st - use min heap to represent available seats, first add to heap all seats from 1-n..
* 2nd - as 1 - optimize. instead of initialize seats 1-n use "marker" */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* use min-heap of size n - represent free seats.
* each "reserve" call - will poll free seat - as it min-heap, will take from smallest indexes.
* each "reserve" call - will again return to free-sit list inside heap.*/
public class B2_SeatReservationManager_1845 {
//    heap - will represent available seat, if its here - its free - if removed from here - is taken.
    PriorityQueue <Integer> heap;
    public B2_SeatReservationManager_1845 (int n) {
//        min heap - that return smallest seat available.
        this.heap = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) { //why 1 and not 0? constraint in theatre, chair index start from 1.
            this.heap.offer(i);
        }
    }
    public int reserve() {
        //if its not in the list - then seat is taken.
        return this.heap.poll();
    }

    public void unreserve(int seatNumber) { //someone cancelled his seat - add it to the availabe seat in pq.
        this.heap.offer(seatNumber);
    }
//    time:  initialize: On log n. reserve-O log n. unreserve-O log n.
//    space: On - min-heap.
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation:
remember constraint - first give low index seats.
* so at first we give them.
* but, if got unreserved - it sure be smaller index than cur "marker" so will give it first!!  */

/*psudo:
 * we want to spare time complexity of first initialization!
 * we do that by keep the min-heap empty. and reserve only by the marker.
 * if someone cancelled - we add its free space to the pq, and before next time reserve - first check
 * that maybe we got something to offer from pq and only then using marker */
class B2_SeatReservationManager_1845_second_solution{
        PriorityQueue<Integer> heap;//empty min heap. represent available seat - all that be here are unreserved seats.
        int marker; //indicate next available seat.

        public B2_SeatReservationManager_1845_second_solution(int n) {
        this.heap = new PriorityQueue<>();
        marker = 1;  //why 1 and not 0? constraint in theatre, chair index start from 1.
    }

    public int reserve() {
            if ( this.heap.isEmpty()){
                return this.marker++;
            } else {
                return this.heap.poll();
            }
    }
    public void unreserve(int seatNumber) {
        this.heap.offer(seatNumber);
    }

//    k==number of "unreserve",
//    time: O log k  intialize - O1, reserve-O1/O log k, unreseve - O log k.
//    space: Ok - heap, store only cancelled seats.
}
