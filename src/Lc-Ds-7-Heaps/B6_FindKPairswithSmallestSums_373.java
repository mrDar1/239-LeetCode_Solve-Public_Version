import java.util.*;
//did not get that!

/*problem:
* given (int[] nums1, int[] nums2, int k)
* return k pairs (1 element from nums1, 1 element from nums2) - that sum are smallest.
* each pair can only use once.*/

//LC official dont work!!! so use instead:
/*solutions:
1st - LC official - for it to work self implement Pair class.
2nd - from comments
3rd - from comments  */

public class B6_FindKPairswithSmallestSums_373 {
        class Pair {
            int num1Index;
            int num2Index;

            Pair(int num1Index, int num2Index) {
                this.num1Index = num1Index;
                this.num2Index = num2Index;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return num1Index == pair.num1Index && num2Index == pair.num2Index;
            }

            @Override
            public int hashCode() {
                return Objects.hash(num1Index, num2Index);
            }
        }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();

//        edge case:
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return ans;
        }

        Set<Pair> visited = new HashSet<>(); //mark all seen nodes - avoid duplicates.

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0])); //first prioritize small then big.
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(new Pair(0, 0));

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int nums1_smallest = top[1];
            int nums2_smallest = top[2];

            ans.add(Arrays.asList(nums1[nums1_smallest], nums2[nums2_smallest]));

            if (nums1_smallest + 1 < nums1.length && !visited.contains(new Pair(nums1_smallest + 1, nums2_smallest))) {
                minHeap.offer(new int[]{nums1[nums1_smallest + 1] + nums2[nums2_smallest], nums1_smallest + 1, nums2_smallest});
                visited.add(new Pair(nums1_smallest + 1, nums2_smallest));
            }

            if (nums2_smallest + 1 < nums2.length && !visited.contains(new Pair(nums1_smallest, nums2_smallest + 1))) {
                minHeap.offer(new int[]{nums1[nums1_smallest] + nums2[nums2_smallest + 1], nums1_smallest, nums2_smallest + 1});
                visited.add(new Pair(nums1_smallest, nums2_smallest + 1));
            }
        }
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* heapify both,
* each time choose smallest of them and run on both - choose cmallest and increase the other.*/
class B6_FindKPairswithSmallestSums_373_my_failTry {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//        edge case:
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<Integer> minHeap1 = new PriorityQueue<>(); //first prioritize small then big.
        PriorityQueue<Integer> minHeap2 = new PriorityQueue<>(); //first prioritize small then big.

//            populate both Heaps (in heap sorted manner.)
        for (int i : nums1){
            minHeap1.offer(i);
        }
        for (int i : nums2){
            minHeap2.offer(i);
        }

        int ptr1 = 0;
        int ptr2 = 0;

//            while (ptr1 <= nums1.length && ptr2 <= nums2.length){
        while ( !minHeap1.isEmpty() && !minHeap2.isEmpty() ){
            if (minHeap1.peek() < minHeap2.peek()){
                ans.add( Arrays.asList( minHeap1.peek() , minHeap2.peek() ) );
                minHeap2.poll();
                continue;
            }
            if (minHeap1.peek() > minHeap2.peek()){
                ans.add( Arrays.asList( minHeap1.peek() , minHeap2.peek() ) );
                minHeap1.poll();
                continue;
            }
        }


        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_FindKPairswithSmallestSums_373_mandyadb1 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//        min heap, prioritize smaller first.
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (k > 0) {
            int[] poll = queue.poll();
            int index1 = poll[1];
            int index2 = poll[2];
            List<Integer> list = new ArrayList<>();
            list.add(nums1[index1]);
            list.add(nums2[index2]);
            res.add(list);
            k--;

            if (index2 + 1 < nums2.length) {
                queue.offer(new int[]{nums1[index1] + nums2[index2 + 1], index1, index2 + 1});
            }
        }
        return res;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_FindKPairswithSmallestSums_373_curio_sity {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        // min-heap, prioritize smallest sum first.
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // populate heap, with all nums1[] elements, and just the first from nums2.
        for (int x : nums1) {
            heap.offer(new int[]{x + nums2[0], 0}); // The sum and the index of the second element in nums2
        }

        // Pop the k smallest pairs from the priority queue
        while (k > 0 && !heap.isEmpty()) {
            int[] pair = heap.poll();
            int sum = pair[0]; // Get the smallest sum
            int pos = pair[1]; // Get the index of the second element in nums2

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[pos]);
            currentPair.add(nums2[pos]);
            ans.add(currentPair); // Add the pair to the result list

            // If there are more elements in nums2, push the next pair into the priority queue
            if (pos + 1 < nums2.length) {
                heap.offer(new int[]{sum - nums2[pos] + nums2[pos + 1], pos + 1});
            }

            k--;
        }
        return ans;
    }
}