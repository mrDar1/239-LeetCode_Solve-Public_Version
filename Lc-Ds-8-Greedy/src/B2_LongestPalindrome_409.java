import java.util.*;

/*problem:
* return length of max-length palindrome, that can be built from char at given String "s"*/

/*solutions:
* 1st - use freqHashmap,"hea-sort" freq
* 2nd - same as 1, with no sort so bit faster.
* 3rd - */
public class B2_LongestPalindrome_409 {
    /*psudo: count freq in hasmap:
    * each paired number - add freq to counter.
    * if not pair - add freq to counter -1 - as the last is not paired. and sign flag for plus one.
    * return counter if flag is set - return counter +1 */
    public int longestPalindrome(String s) {
        HashMap <Character , Integer> map = new HashMap<>(); //<letter, freq>
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a); //max heap - get out first the highest number.
        int sumofpalindrome = 0;
        boolean plus_one = false; //flag to add +1 for the non-even number

//        populate freq hashmap:
        for (Character c : s.toCharArray()){
            map.put(c , map.getOrDefault(c, 0) +1 );
        }

//        put freq inside max-heap
        for (int  val : map.values()){
            pq.offer(val);
        }

        while ( !pq.isEmpty()){
            int cur = pq.poll();

            if (cur < 1){ //optimization, use max-heap so if reach 0, can stop iterating.
                break;
            }

            if ( cur % 2 == 0){ //if its pair number - add all its freq to sum.
                sumofpalindrome += cur;
            }

            if ( cur % 2 != 0){ //if odd number - add all its freq minus the last one (as its not pair)
                plus_one = true;
                sumofpalindrome += cur - 1;
            }
        }

        return sumofpalindrome = plus_one ? ++sumofpalindrome : sumofpalindrome ;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B2_LongestPalindrome_409_1_more_elegant {
    public int longestPalindrome(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        int ans = 0;
        boolean hasOddFrequency = false;

        for (int freq : frequencyMap.values()) {
            if ((freq % 2) == 0) {
                ans += freq;
            } else {
                ans += freq - 1;
                hasOddFrequency = true;
            }
        }

        if (hasOddFrequency) {
            return ans + 1;
        }

        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation:
* as build freqHashmap, each time freq is not eqal - count++
* when it do - count--.
* at end return the original size , minus count of all odd number freq, +1 for the middle*/
class B2_LongestPalindrome_409_1_optimized {
    public int longestPalindrome(String s) {
        int oddFreqCharsCount = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);

            if ((frequencyMap.get(c) % 2) == 1) {
                oddFreqCharsCount++;
            } else {
                oddFreqCharsCount--;
            }
        }

//        time: O1n - while build freq hashmap also count.
//        space:O1n - freqHashmap
        if (oddFreqCharsCount > 0) {
            return s.length() - oddFreqCharsCount + 1;
        } else {
            return s.length();
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* build HashSet - when not contain - add, when do contain remove and add to count +2.
* before return count, if set.size() > 0 do +1 for middle*/
class B2_LongestPalindrome_409_Hashset_way{
    public int longestPalindrome(String s) {
        Set<Character> characterSet = new HashSet<>();
        int ans = 0;

        for (char c : s.toCharArray()) {
            if (!characterSet.contains(c)) {
                characterSet.add(c);
            } else {
                characterSet.remove(c);
                ans += 2;
            }
        }

//        time: O1n.
//        space: O1n/O1 - at worst case when all char are different / O1-52 - number of english upper+lower letter.
        if (!characterSet.isEmpty()) ans++;
        return ans;
    }
}