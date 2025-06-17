import java.util.*;

/*problem:
* return k elements that are closes to (0,0) (==smallest elements).
* note: return the actual values not index nor anything else.
* each size calculate with roots formula.
* constraint: answer is guaranteed to be unique. */

/* solutions - only 1,5 are important the rest are practice:
1st - the trivial sort.
2nd - self implement sort with array.
3rd - self implement sort with hashmap.
4th - heap - very nice!
5th - heap - optimized!!            ------- the best!!!
6th - same as 6, different way to use the sort. */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* sort points[][] - smallest values first.
* how the sort works:
*   1st-calculate distance of all inner-array (with tailor made method for it).
*   2nd-the smallest distance would be put at front.
*   3rd-"forget" the distance of each inner-array. as now it doesnt matter as all sorted already.
* return first k smallest inner-array */
//1st - sort:
public class A10_KClosestPointstoOrigin_973 {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> getDist(a) - getDist(b)); //increasing-sort points[], according to distance of each point[][].
        return Arrays.copyOf(points, k); //return only first k elements.
    }

    public int getDist(int[] point) {//auxiliary method - for the roots formula (didnt do the root itself as shortcut, save calculate time, as its enough for this problem)
        return point[0] * point[0] + point[1] * point[1];
    }

//    time: O n log n - sort.
//    space: avg of log n, as java use variant of quicksort.
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* create helper-calculateArr, which is copy of points[][], add 1 more column-curDistance.
* sort helper-calculateArr according to added column-curDistance
* copy to ansArr first K elements, do not copy helper.  */
//2nd:
class A10_KClosestPointstoOrigin_973_redid_1_with_arr {
    public int[][] kClosest(int[][] points, int k) {

//        helper arr - added distance parameter, later sort by it.
        Integer[][] calculateArr = new Integer[points.length][3]; //[0]==x,[1]==y,[2]==distance
        for (int i = 0; i < points.length; ++i) {
            calculateArr[i][0] = points[i][0];
            calculateArr[i][1] = points[i][1];
            calculateArr[i][2] = getDist(points[i]); //store distance.
        }

        Arrays.sort(calculateArr, (a, b) -> a[2].compareTo(b[2])); //increasing-sort points[], according to distance of each point[][].

        //copy to ansArr k first element, do not copy helper-distance-column.
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i) {
            ans[i][0] = calculateArr[i][0];
            ans[i][1] = calculateArr[i][1];
        }

//        time  On log n : O1n-populate "calculateArr", Onlogn-sort, O1k-copy first k elements to ans.
//        space: O1n + log n - "calculateArr". do not count output ans. Olog n-sort, as java use variant of quicksort.
        return ans;
    }

    public int getDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd:
class A10_KClosestPointstoOrigin_973_redid_1_with_hashmap {
    public int[][] kClosest(int[][] points, int k) {
        HashMap<Integer, List<Integer[]>> map = new HashMap<>(); //store: <distance, points[][]>

        for (int[] arr : points) {
            Integer[] temp = new Integer[2];
            temp[0] = arr[0];
            temp[1] = arr[1];

            int dist = getDist(arr);
            map.putIfAbsent(dist, new ArrayList<>());
            map.get(dist).add(temp);
        }
//        another way to do above loop (with regular "for"):
//        for (int i = 0; i < points.length; ++i) {
//            Integer[] temp = new Integer[2];
//            temp[0] = points[i][0];
//            temp[1] = points[i][1];
//
//            int dist = getDist(points[i]);
//            map.putIfAbsent(dist, new ArrayList<>());
//            map.get(dist).add(temp);
//        }

        // create helperList of all distance and sort it
        List<Integer> distancesList = new ArrayList<>(map.keySet());
        Collections.sort(distancesList);

        // traverse helperList and extract with hashmap the first K, add to ans.
        int[][] ans = new int[k][2];
        int c = 0;//counter how many elements in our array - want only k.
        for (Integer currDistance : distancesList) {
            for (Integer[] point : map.get(currDistance)) {
                if (c < k) {
                    ans[c][0] = point[0];
                    ans[c][1] = point[1];
                    c++;
                } else {
                    break;
                }
            }
            if (c >= k) {
                break;
            }
        }

//        time:  O: O1n-create helper-Hashmap,  Onlogn - Sort, O1k-copy to ans first K elements.
//        space: On + log n - hashmap. do not count output ans. Olog n-sort, as java use variant of quicksort.
        return ans;
    }

    public int getDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* create Min-heap - "heap-sort" values according to calculated distances.
* copy first K elements to ans[][]. */
//4th:
class A10_KClosestPointstoOrigin_973_min_heap {
    public int[][] kClosest(int[][] points, int k) {
//        init min-heap so smaller prioritized first - poll first. remain with good smaller values later poll to ans.
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> getDist(a) - getDist(b));

//        populate heap
        for (int[] arr : points){
            heap.offer(arr);
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++){
            result[i] = heap.poll();
        }

//        time: O n log k
//        space: On - for heap.
        return result;
    }
    private int getDist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* create Max-heap - "heap-sort" values according to calculated distances.
* traverse points[][] - each time heap.size bigger than k - remove the bigger values - as they are
* surly not ans.
* convert heap to ans.*/
//5th:
class A10_KClosestPointstoOrigin_973_my_fav_with_min_heap_optimize {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> getDist(b) - getDist(a));

//        populate heap
        for (int[] arr : points){
            heap.offer(arr);
            if (heap.size() > k){
                heap.remove();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++){
            result[i] = heap.poll();
        }

//        time: O n log k
//        space: Ok - for heap.
        return result;
    }
    private int getDist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//6th:
class A10_KClosestPointstoOrigin_973_my_fav_with_min_heap_optimize_see_different_sort {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);

//        populate heap
        for (int[] arr : points){
            heap.offer(arr);
            if (heap.size() > k){
                heap.remove();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++){
            result[i] = heap.poll();
        }

//        time: O n log k
//        space: Ok - for heap.
        return result;
    }
}