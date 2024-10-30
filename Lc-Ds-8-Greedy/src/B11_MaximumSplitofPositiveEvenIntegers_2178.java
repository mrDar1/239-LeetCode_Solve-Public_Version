import java.util.ArrayList;
import java.util.List;
/*problem:
* given single int - return List of max unique-even-number that can sum to it */

/*motivation: start from 2, each round add +2 until reach number whos bigger than target.
* remove according num.
* if cur==target return true.*/
public class B11_MaximumSplitofPositiveEvenIntegers_2178 {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        long cur = 0;
        long sumsofar = 0;

//        edge case - odd number.
        if (finalSum % 2 != 0){
            return ans;
        }

        while (sumsofar < finalSum){
            cur += 2;
            sumsofar += cur;
            ans.add(cur);
        }

        if (sumsofar > finalSum){
            long dif = sumsofar - finalSum;

            ans.remove(((int) (dif / 2) - 1)); //give index!!
            // must convert to (int) - so remove .0 - and be in right place of arr.
//            above line could be replace with:
//            ans.remove(dif); //search value in List!!!
            /*the diffrence between them both is second line - searching in the List for this element - what
            * takes a long time (beats only 33%).
            * however what used here - is the list index!!! so its much faster!! (beats 86%)!!!!  */
        }

//      time: O sqrt(finalSum) :
//      space:O1 - do not count output List.
        return ans;
    }
}