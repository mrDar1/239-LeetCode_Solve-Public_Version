import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*problem: given text - return how many times you can assemble the pattern "balloon"*/

/*solutions:
* 1- use 2 hashmap - and generalized the solution to any pattern */

/* production management problem.
Assume there is an industry that produces a product X.
The product X can be produced by assembling one instance of each of five different parts.
We have some fixed quantity of each of these parts, then the maximum number of product X
that can be produced will be equal to the quantity of that part which is available in the
least quantity. This least available part is known as the bottleneck resource.
In this problem, the product X is the string balloon and the five parts are
the characters b, a, l, o, and n. */

public class A14_MaxNumberOfBallons_1189 {
    public static void main(String[] args) {
//        String s = ("lloo"); // ans 0
//        String s = ("nlaebolko"); // ans 1
        String s = ("bllo"); // ans 0
        System.out.println(maxNumberOfBalloons(s));
    }

/*psudo:
for ans to work not only with "balloon" but with any given string - use helper method to generalize the problem.
1-use 2 hashmap to count char freq inside "pattern" & "text". <char, freq>
- optimization: for text hashmap - we only enter keys that are at "pattern" otherwise we dont count them.
2-traverse again all "text" hashmap keys - update freq / freq at pattern: so for "balloon"-got 1-b,1-a,2-l,2-o,1-n - we need to reduce sized.
3-after update all freq of "text" hashmap - check what is the min value - and return it - as these "bottleneck" limits our "production management".

got some neat trick: when initialize "pattern" hashmap - also initialize "text" hashmap with 0 - so when search min val at it - will get zero - if missing completely one of the element.
 */
    public static int maxNumberOfBalloons(String text){ //start here
        String pattern = "balloon";
        return maxNumberOfPatterns(text, pattern);
    }
    public static int maxNumberOfPatterns(String text, String  pattern){
        Map<Character, Integer> patternmap = new HashMap<>();   // <char ,freq>
        Map<Character, Integer> textmap = new HashMap<>();      // <char ,freq>
        int min = Integer.MAX_VALUE;

//        count freq of letters in "pattern"
//        and - initialize text map - so in next hashmap we only hash relevant characters.
        for( char c : pattern.toCharArray()){
            patternmap.put(c, patternmap.getOrDefault(c, 0) + 1);
            textmap.put(c, textmap.getOrDefault(c, 0) );
        }

//        count freq of letters in "text" - optimize - choose only letters that appear in "pattern"
        for( char c : text.toCharArray()){
            if ( null != patternmap.get(c)){ //count only letters that appear in "pattern"
                textmap.put(c, textmap.getOrDefault(c, 0) + 1);
            }
        }

//        iterate text hashmap - and "divide /" each letter with the number of times we need it to assemble the pattern.
//        for example - in pattern "balloon" we have 1b, 1a, 2l, 2o, 1n.
        for ( Character key: textmap.keySet() ){
            textmap.put(key, textmap.getOrDefault(key, 0) / patternmap.get(key) );
        }

//        debugging: check if the '/' work - and it did!!
//        for ( Map.Entry<Character, Integer> iterator: textmap.entrySet() ){
//            System.out.println(iterator.getKey() + " = " + iterator.getValue() );
//        }

//        iterate text hashmap and search Min val - as we limited to that "bottleneck".
        for ( Map.Entry<Character, Integer> iterator: textmap.entrySet() ){
            min = Math.min(min, iterator.getValue());
        }

//        n==pattern len
//        m==text len
//        time: O3n+m==O1: O1n - build hashmap of pattern, O1m - build hashmap of text, O1n-devide freq with pattern freq,  O1n-find min value at text hashmap.
//        space: O1n+1m - hashmap of text and pattern
//        one can argue its O1-as english letters are 26...
        return min;
    }
}









/*
//    my first fail attempt - work only here but not at LC:
//    psudo: traverse and count each char. then /2 l,n - because we need them twice!
//    then create hashset and copy all values.
//    traverse hashset and find min value;
    public static int maxNumberOfBalloons_not_good_try(String text) {
        HashMap<Character,Integer> count = new HashMap<>();
        HashSet<Integer> removeduplicate = new HashSet<>();
        HashSet<Character> origin = new HashSet<>();
        int ans = Integer.MAX_VALUE;
        String s = "balon";

//        edge case:
        if (text.length() < s.length()) return 0;

//        building origin hashset number of char we need:
        for(Character c : s.toCharArray()){
            origin.add(c);
        }

//        count only char we have in origin:
        for (Character i : text.toCharArray()){
            if(origin.contains(i)){
                count.put(i, count.getOrDefault(i, 0) + 1);
            }
        }
//      check debugging:
//        for ( Map.Entry<Character, Integer> iterator: count.entrySet() ){
//            System.out.println(iterator.getKey() + " = " + iterator.getValue() );
//        }
//        System.out.println( "after removing:");

        if (count.get('l') == null || count.get('o') == null) return 0; // so we wont /0 !!!
        count.put('l', count.getOrDefault('l', 0) / 2);
        count.put('o', count.getOrDefault('o', 0) / 2);

//      check debugging:
//        for ( Map.Entry<Character, Integer> iterator: count.entrySet() ){
//            System.out.println(iterator.getKey() + " = " + iterator.getValue() );
//        }

        removeduplicate.addAll(count.values());

        for (int i : removeduplicate){
            ans = Math.min(ans, i);
        }

//        time: O3n - hashmap counting, hashset copy and hashset traversal.
//        space: O1n+k hashmap + k size of pattern (=O1 english letters only 26)
        return ans;
    }
}*/
