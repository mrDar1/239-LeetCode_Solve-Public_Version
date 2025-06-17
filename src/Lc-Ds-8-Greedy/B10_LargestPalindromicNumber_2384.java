import java.util.HashMap;
/*problem:
* return largest number whose also a palindrome, from given String num.
* can change order.*/

/*psodu:
* traverse num and create hashmap to count freq
* use max-heap pq to store data inside map - save only freq(=values).
* prioritize first highest pair as start and last.
* for comfortable - build only first half palindrome as SB, then reverse and append it again.
* before append second part, add highest odd number.*/
public class B10_LargestPalindromicNumber_2384 {
    public String largestPalindromic(String num) {

//        edge case:
        if (num.isEmpty() ){
            return ("0");
        }

        StringBuilder sb = new StringBuilder(); //the first half of the palindrome.
        StringBuilder ans = new StringBuilder(); //the full palindrome - use at end.
        Character largest_single = null; //for the lonely number that will be in middle of palindrome.
        HashMap<Character, Integer> map = new HashMap<>(); //freq map <char, occurrences >

//        populate hashmap:
        for (char c : num.toCharArray()){
            map.put(c , map.getOrDefault(c , 0) + 1);
        }

//        start from biggest digits and get lower
        for (char c = '9' ; c >= '0' ; c--){
            int curr_freq = map.getOrDefault(c ,0);

            if (sb.length() < 1 && c == '0'){ //edge case - only 1 digit and all leading zeroes.
                return largest_single != null ? (largest_single.toString()) : "0";
            }

            if (curr_freq % 2 == 1 && largest_single == null){//flag to middle number.
                largest_single = c;
            }

            int temp = curr_freq / 2;
            while (temp--  >  0){
                sb.append(c);
            }
        }

        ans.append(sb);
        if (largest_single != null){
            ans.append(largest_single);
        }
        ans.append(sb.reverse());

        return ans.toString();
    }
}