import java.util.*;
/*problem:
Given an array of strings words and an integer k, return the k most frequent strings.
Return the answer sorted by the frequency from highest to lowest.
words with the same frequency Sort by their lexicographical order */

/*solutions:
all of them using freq hashmap:
1st - classic sort, then return only first k (biggest) elements.
2nd - as 1st, use max-heap - improve complexity.
3rd - as 2nd, use min-heap - to even better improve complexity.       best! fast and          unlike the others, can work with dynamically added new "words" with unique freq.
4th - some experiment, build "auto"-comparator, under List class. */

public class B1_TopKFrequentWords_692 {
    /*psudo:
     * 1st-build hashmap to count occurrences.
     * 2nd-copy all hashmap into ans List, and sort it.
     *      the sort: most freq Strings will be at start, if freq equal - sort lexicographical.
     * 3rd-return first K element from sorted List. */
//    1st:
    public List<String> topKFrequent_hashmap_classic_sort_way(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();//<word, num of occurrence>
        List<String> ans = new ArrayList<>();

//        build freq hashmap:
        for (String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        ans.addAll(map.keySet());
//        above line can be replaced with:
//        List<String> ans = new ArrayList<>(map.keySet());

        Collections.sort(ans, (w1,w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1));

        return ans.subList(0, k); //return k first element.
//        time:  O n*(log n) == O3n(log n). 2n - build hashmap and copy it to ansList. O-nlog n-sort.
//        space: O(n) == O(2n) - hashmap and ansList
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*motivation:
    * same idea as 1, but with max-heap.*/
//    2nd
    public List<String> topKFrequent_as_1_max_heap(String[] words, int k) {
//        build hashmap for occurrences:
        HashMap<String, Integer> map = new HashMap<>(); //<word, num of occurrence>
        for (String w : words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        //initialize that each element added to heap - "auto" prioritize most-freq elements.
        PriorityQueue <String> heap = new PriorityQueue<>((w1,w2) ->
                map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1) );

        for (String cur_word : map.keySet()){//populate heap, he will "auto" prioritize most freq first.
            heap.offer(cur_word);
        }

//        convert from heap to ArrList
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(heap.remove());
        }
        return ans;
//        time: Onlogn  : O1n-freq hashmap, On log n-max_heap added n elements. O1k-convert from heap to ArrList.
//        space: O1n+O1k: O1n - hashmap, O1k - ans.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
     * 1st-use hashmap to count freq.
     * 2nd-use a min heap to store values from hashmap.
     *      "heap-sort": first prioritized words with fewer occurrences (so can remove and remain with good high occurrences)
     * 3rd-question constraint - we care only from the first k most occurred words so:
     * each time pq bigger than k - we remove lesser occurred values.
     * 4th-we empty pq into ans List
     * 5th-reverse the list - as it is min-heap so less freq will be first. */
//    3rd:
    public List<String> topKFrequent_improve2_min_heap(String[] words, int k) {
//        build hashmap for occurrences:
        HashMap<String, Integer> map = new HashMap<>(); //<word, num of occurrence>
        for (String w : words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        PriorityQueue <String> heap = new PriorityQueue<>((w1,w2) ->
                map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2) );

        for (String cur_word : map.keySet()){
            heap.offer(cur_word);
            if (heap.size() > k){ // constraint: want only the most freq-so if too small cannot be the ans and can remove.
                heap.poll();
            }
        }

        List<String> ans = new ArrayList<>();
        while (!heap.isEmpty()){
            ans.add(heap.poll());
        }

        Collections.reverse(ans);

//        time:  On log k == O3n + 2(k log k): 1n-hashmap, O1n log k-build heap add and remove elements. O1n-copy heap to List, O 1n - reverse.
//        space: O1n + 1k: n-hashmap, k-heap (do not count ansList output stream)
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * 1st-build hashmap to count occurrences.
    * 2nd-copy that map into List-easier to work with.
    * 3rd-copy from List into Pq - that we manually make max-heap - the heap make it sort.
    * 4th-create ans List.
    * 5th-using a loop K'th time - each timme pop from Pq - and add to list.  */
//    4th
    class Word implements Comparable<Word> {
        private String word;
        private int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public int compareTo(Word other) {
            if (this.count == other.count) {
                return this.word.compareTo(other.word);
            }
            return other.count - this.count;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }

        List<Word> candidates = new ArrayList<>();
        for (Map.Entry< String, Integer> entry : cnt.entrySet()) {
            candidates.add(new Word(entry.getKey(), entry.getValue()));
        }

        Queue<Word> h = new PriorityQueue<>(candidates);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(h.poll().word);
        }
//            time:Onlogn== O2n + k*logn  1st-O1n build hashmap. 2nd-O1n-build convert tmp list from hashmap. 3rd-1n-build heap from list. 4th - O k*log n-removing k most occurred words
//            space: O2n - 1st-1n-hahsmap 2nd-1n-tmp List, 3rd-k-size of ans List but do not count output space.
        return res;
    }
}


