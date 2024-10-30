import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*problem:
return lowest num of boats to save people.
each boat can contain 1 person at any weight.
or each boat can take 2 persons if they under weight limit. */

/*motivation:
* greedily try to match pairs of most skinny with fattest. as
* if it work - great!
* if fail, give fat boat of his oun and try to match the next fat with skinny. */

/*solution-same 1 readable, 2nd more elegant*/

public class A5_BoatstoSavePeople_881 {
    public int numRescueBoats_my_first_try(int[] people, int limit) {
        int rptr = people.length - 1;
        int lptr = 0;
        int sum_boats = 0;

        Arrays.sort(people);

        while (lptr <= rptr){
            if (people[lptr] + people[rptr] > limit){
                --rptr;
            }else {
                --rptr;
                ++lptr;
            }

            ++sum_boats;
        }
//        time: O1n
//        space: O1
        return sum_boats;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int numRescueBoats_second_more_elegant(int[] people, int limit) {
        int ans = 0;
        int left = 0;
        int right = people.length - 1;

        Arrays.sort(people);

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }

            right--;
            ans++;
        }

        return ans;
    }
}