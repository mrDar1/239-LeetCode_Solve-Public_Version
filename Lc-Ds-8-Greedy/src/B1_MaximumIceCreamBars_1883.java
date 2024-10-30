import java.util.PriorityQueue;
/*problem:
* given "coins" return max ice cream that can buy.
* each ice cream at different price as "costs[]" */


/* 2 solutions:
* 1-mine first time success.
* 2-nd use Counting sort here only for study. did not solve that way.*/
public class B1_MaximumIceCreamBars_1883 {
    public int maxIceCream(int[] costs, int coins) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b); //min heap. (do not need lambda here its for practice.)
        int sum = 0;

        for (int i : costs){
            pq.offer(i);
        }

        while (coins > 0 && !pq.isEmpty()){
            int cur_ice_price = pq.poll(); //price of current ice cream

            if (cur_ice_price <= coins){
                ++sum;
                coins -= cur_ice_price;
            } else { // when dont have enough money or buy all ice cream on store.
                return sum;
            }
        }
//        time: On log k -  O1n (each time is log n n times) - build PQ, On log k - remove from heap.
//        space: O1n.
        return sum;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int maxIceCream_Counting_Sort(int[] costs, int coins) {
        int icecreams = 0;

//        find max value at costs[] - will be bucket sort arr boundary.
        int m = costs[0];
        for (int cost : costs) {
            m = Math.max(m, cost);
        }

//        regular bucket sort
        int[] buckSort = new int[m + 1];
        for (int cost : costs) {
            buckSort[cost]++;
        }

        for (int cur_ice_price = 1; cur_ice_price <= m; ++cur_ice_price) {
            if (buckSort[cur_ice_price] == 0) { //no ice cream at that price
                continue;
            }
            if (coins < cur_ice_price) { //not enough money to buy - from here its only more expensive so break.
                break;
            }

//            firsts are cheapest, so will want to buy as many ice-cream as can.
//            limit: not enough money / not enough ice cream at that price.
            int count = Math.min(buckSort[cur_ice_price], coins / cur_ice_price);

            coins -= cur_ice_price * count;
            icecreams += count;
        }

        return icecreams;
    }
}