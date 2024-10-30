import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*problem:
* return min number of substring to make original string valid.
* to valid:
* each time char appear more than once divide to another substring.*/

/*solutions::
* 1-use a seen set for the char, each duplicate "clear" set.
* 2-close to 1st, implement with arr. */
public class B8_OptimalPartitionofString_2405 {
    public static void main(String[] args) {
        Solution_B8_OptimalPartitionofString_2405 obj_2405 = new Solution_B8_OptimalPartitionofString_2405();
        System.out.println(obj_2405.partitionString_my_first_approach("abacaba")); // Output: 4
        System.out.println(obj_2405.partitionString_my_first_approach("ssssss")); // Output: 6
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B8_OptimalPartitionofString_2405{
        public int partitionString_my_first_approach(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 1; //if no duplicate - will be the original one==1 substring..

        for (char c : s.toCharArray()){
            if ( !set.contains(c) ){
                set.add(c);
            } else {
                set.clear();
                set.add(c);
                ++ans;
            }
        }
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int partitionString(String s) {
        int[] lastSeen = new int[26]; //0-25 to represent english a,b,c.
        Arrays.fill(lastSeen, -1); //all fill with -1, if found a char - will put here its index.
        int ans = 1;
        int subStringHead = 0;


        for (int i = 0 ; i < s.length() ; ++i){
            if ( lastSeen[s.charAt(i) - 'a'] >= subStringHead){//if found duplicate - start next substring from its location.
                ++ans;
                subStringHead = i;
            }

            lastSeen[s.charAt(i) - 'a'] = i; //update location of seen letter.
        }
//        time:  O2n. O1n-fill locations with -1, O1n-traverse s.
//        space: O1-26 english letters.
        return ans;
    }
}