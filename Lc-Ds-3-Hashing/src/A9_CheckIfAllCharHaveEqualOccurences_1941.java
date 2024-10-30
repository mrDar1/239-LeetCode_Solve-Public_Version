import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*problem: return true is s is "good string", false otherwise.
* good string: all char has same freq. */

public class A9_CheckIfAllCharHaveEqualOccurences_1941 {
    public static void main(String[] args){
        String s = new String("abacbc");
        System.out.println(areOccurrencesEqual(s));
//        System.out.println(areOccurrencesEqual2(s));
    }
    public static boolean areOccurrencesEqual(String s){
/*    psudo: build hash map and count occurrences,
    enter to hashset and check size==1; */

        HashMap<Character, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        set.addAll(map.values());
        //time: O2n - first hashmap, 2nd addAll to hashset.
        // space: O1 - english aplphabet - could say its Ok. K=number of characters that could be in the input.
        return (1 == set.size());
    }
}



/* last time did it less good:
* public static boolean areOccurrencesEqual(String s){
        int fno = 0; //first number of occurences - how many times occur the first element - all suppose to be equal!
        Map<Character, Integer> occurence_count = new HashMap<>();

//        building the hashmap: <char, number of occurences>
        for(char c : s.toCharArray()){
            occurence_count.put(c, occurence_count.getOrDefault(c, 0) + 1);
        }
//      number of occurrence the first element - all suppose to be equal!
        fno = occurence_count.get(s.charAt(0));

        System.out.println("debugging hashing iterator:");

//        run on each char from the map, if its value(number of occurences) diffrent than that of the first - return false.
        for(Map.Entry<Character, Integer> set: occurence_count.entrySet()){
            if(set.getValue() != fno){
                return false;
            }
//            debugging hashing iterator:
            System.out.println("key: " + set.getKey() + " val: " +  set.getValue());
        }
        System.out.println(" "); //debugging
        return true;
        //time: O2n : 1st: hashing 2nd: iterate the hashmap
        // space: O1 - english aplphabet - could say its Ok. K=number of characters that could be in the input.
    }

    public static boolean areOccurrencesEqual2(String s){
        Map<Character, Integer> counts = new HashMap<>();

//        creating the hashmap: <char, number of occurences>
        for (char c: s.toCharArray()){
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

//        creating hashsets: - note its take the hashmap values and add each one.
        Set<Integer> frequencies = new HashSet<>(counts.values());
        return frequencies.size() == 1;
        //time: O2n - 1st: hashmap , 2nd: iterate the hashmap for hashset.
        // space: O1 - english aplphabet - could say its Ok. K=number of characters that could be in the input.
    }
}
*
*
* */