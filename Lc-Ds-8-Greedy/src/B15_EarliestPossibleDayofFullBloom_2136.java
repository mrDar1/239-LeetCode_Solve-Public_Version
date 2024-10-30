import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/*problem:
* return the min day that all flowers bloom.
* bloom require 2 things:
* 1st - "plantTime[]" - time it take to seed it. can only done 1 at a time.
* 2nd -  "growTime[]" - time must wait before it ready, can be several together.*/

/*intuition:
first plant the plants with most growth time - so wont waste time by waiting it to grow.
and so first plant them. and check number of days. */

public class B15_EarliestPossibleDayofFullBloom_2136 {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {

//        traverse growTime[] and sort in descending growth time.
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < growTime.length; ++i) {
            indices.add(i);
        }
        indices.sort(Comparator.comparingInt(i -> -growTime[i])); //sort in descending growth time.
//          above line could replaced with:
//        Collections.sort(indices, Comparator.comparingInt(i -> -growTime[i])); //sort in descending growth time.
//          and also replaced with:
//        Arrays.sort(indices, (a,b) -> Integer.compare( growTime[b], growTime[a]) );


        int ans = 0;
        for (int i = 0, curPlantTime = 0; i < growTime.length; ++i) {
            int idx = indices.get(i);
            int time = curPlantTime + plantTime[idx] + growTime[idx];
            ans = Math.max(ans, time);
            curPlantTime += plantTime[idx];
        }
        return ans;
    }
}
