import java.util.*;

/*problem:
Given a non-empty int[] nums,
every element appears twice except for one - which appear once.
return that single one. */

/*solutions:
* 1st - use "seen" hashset
* 2nd - use count freq hashmap
* 3rd - use List (as hashset so slower)- learned here something new - about java collections method overloading.
* 4th - Gauss formula with "seen" hashset
* 5th - Gauss formula with bitmask */

public class A7_SingleNumber_136 {
    public int singleNumber(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int i : nums){
            if( !seen.contains(i)){
                seen.add(i);
            } else {
                seen.remove(i);
            }
        }
//        time: O1n
//        space: O.5n==On (at worst case, avg will be On/2 - since all are pairs except one so its 0.5n + 1).
        return seen.iterator().next();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Z2_SingleNumber_136_use_freq_hashmap {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i : nums){
            seen.put(i , seen.getOrDefault(i, 0) + 1);
        }

        for ( int key : seen.keySet()){
            if (seen.get(key) == 1){
                return key;
            }
        }

//        time: O2n - traverse twice.
//        space: O1n - hold entire int[].
        return 0;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Z2_SingleNumber_136_hash_List {
    public int singleNumber(int[] nums) {
        List<Integer> seen = new ArrayList<>();

        for (int i : nums){
            if (seen.contains(i)){
                seen.remove(new Integer(i));
                /* Q: why do we need here "new Integer(i)?"
                 A: java List interface is part of Collections Framework and is defined with generics.
                 got 2 ways to remove from list (different method overloading):
                 1st - remove according to index: "E remove(int index);"
                 2nd - remove according to value: "boolean remove(Object o);"
                 at cur problem, we want to find that value and remove it - but if we send simply i - which is integer,
                 List Collections method overload, will search the element number i and remove it.
                 so to avoid that - when we use " Integer(i)" - java sees it as object and use the second one
                 that we want.

                 Q: "E remove(int index);" what is E stand for ?
                 A: "element" as List can hold different data-types of elements.
                 and when we do "List<Integer>" - it becomes Integer data-types elements List. */
            }else {
                seen.add(i);
            }
        }
//        time: On^2 - for each nums[] we need to search inside list that element.
//        space: O1n
        return seen.get(0);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Z2_SingleNumber_136_Gauss_formula {
    public int singleNumber(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        int sumffUniqeElement = 0;
        int SumofAllElements = 0;

        for (int i : nums) {
            SumofAllElements += i;
            if (!seen.contains(i)) {
                seen.add(i);
                sumffUniqeElement += i;
            }
        }
        return 2*sumffUniqeElement - SumofAllElements;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation:
each time Xor encounter number who has "seen" before will make it 0, remain only with the unique. */
class Z2_SingleNumber_136_use_Gauss_formula_Bitwise {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for (int i : nums){
            ans ^= i;
        }
//        time: O1n
//        space:O1
        return ans;
    }
}