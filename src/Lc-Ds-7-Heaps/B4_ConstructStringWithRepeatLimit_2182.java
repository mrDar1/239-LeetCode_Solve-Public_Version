import java.util.*;
/*problem:
* given String s and K-repeatLimit.
* we need to sort s so will be lexicographic largest!
* but, each letter cannot appear more than k times. put 1 char of next lexicographic largest, and back to largest!

/*solutions:
1st - classic solution - with maxHeap and hashmap.
2nd - as 1 with array.
3rd - as 1st, practice Pair classes */

/*psudo:
 * hashmap/array of size 26(english char) to count occurrences of each char.
 * max-heap - prioritize first lexicographic bigger.
 * fill in ans StringBuilder until "repeatLimit" or as many time got this char occurrences.
 *      2 ways:
 *      1-if what limited was number of occurrences - continue to next char.
 *      2-if what limited was "repeatLimit" - put 1 of the next char, offer back to heap the "next" and "cur":
 *      so can repeat process again next round. */
public class B4_ConstructStringWithRepeatLimit_2182 {
    public static String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder ans = new StringBuilder();
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> b - a); // max-heap prioritize lexicographically larger char.

        Map<Character, Integer> freqMap = new HashMap<>(); //freq hashmap <char, freq>
        // populate freq hashmap:
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        // populate maxHeap from hashmap (sort them in lexi-first):
        for (char c : freqMap.keySet()) {
            maxHeap.offer(c);
        }

        while (!maxHeap.isEmpty()) {
            char cur = maxHeap.poll();

            for (int i = 0; i < repeatLimit; i++) {
                ans.append(cur);
                freqMap.put(cur, freqMap.get(cur) - 1); // each add - update freq with -1.

                if (freqMap.get(cur) == 0){
                    break;
                }
            }

            if (freqMap.get(cur) > 0 && !maxHeap.isEmpty()) { // put 1 of next char
                char next = maxHeap.poll();
                ans.append(next);
                freqMap.put(next, freqMap.get(next) - 1); //update "freq inventory map"

                // if next char still has occurrences left, put it back in the heap for next round
                if (freqMap.get(next) > 0) {
                    maxHeap.offer(next);
                }
                maxHeap.offer(cur);
            }
        }
//        time:  O1n - build hashmap, n log n insert map into heap, O k*(log n)-each time poll from heap, put in sb until k and then reoffer it, poll next and then reoffer both nodes.
//        space: O2n, hashmap + heap.
        return ans.toString();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B4_ConstructStringWithRepeatLimit_2182_array_count_freq {
    public static String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder ans = new StringBuilder();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max-heap to prioritize lexicographically larger characters
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Populate maxHeap from frequency array
        for (int i = 25; i >= 0; i--) { // Starting from 'z' to 'a'
            if (freq[i] > 0) {
                maxHeap.offer((char) (i + 'a'));
            }
        }

        while (!maxHeap.isEmpty()) {
            char cur = maxHeap.poll();
            int count = Math.min(freq[cur - 'a'], repeatLimit);

            // Append the current character up to count times
            for (int i = 0; i < count; i++) {
                ans.append(cur);
            }

            freq[cur - 'a'] -= count; // Update frequency

            if (freq[cur - 'a'] > 0) {
                if (maxHeap.isEmpty()) {
                    break;
                }

                // Get the next largest character
                char next = maxHeap.poll();
                ans.append(next);
                freq[next - 'a']--;

                // If the next character still has occurrences left, put it back in the heap
                if (freq[next - 'a'] > 0) {
                    maxHeap.offer(next);
                }
                maxHeap.offer(cur);
            }
        }
        return ans.toString();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B4_ConstructStringWithRepeatLimit_2182_playwithpairclass {
    public static String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder sb = new StringBuilder();
//        max heap to hold max vals, first prioritize lexicographic bigger.
        PriorityQueue<myPair> pq = new PriorityQueue<>((a,b)-> b.letter - a.letter );

//        build freq hashmap <char, freq>
        Map <Character ,Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c , 0) + 1);
        }
//        populate pq with freq hashmap.
        for (Map.Entry<Character ,Integer> node : map.entrySet()){
            pq.offer(new myPair(node.getKey(), node.getValue()) );
        }

        while ( !pq.isEmpty()){
            myPair node = pq.poll();

            for (int i = 0; i < repeatLimit; i++) {
                sb.append(node.letter);
                node.freq--;

                if (node.freq == 0){
                    break;
                }
            }

            if(node.freq > 0){
                if ( !pq.isEmpty()){ //put 1 time next char and then offer cur char ans start again.
                    myPair node_2 = pq.poll();

                    sb.append(node_2.letter);
                    node_2.freq--;

                    if (node_2.freq > 0){
                        pq.offer(node_2);
                    }
                    pq.offer(node);
                }
            }
        }
        return sb.toString();
    }
}
class myPair{
    char letter;
    int freq;
    myPair(char letter, int freq){
        this.letter = letter;
        this.freq = freq;
    }
}