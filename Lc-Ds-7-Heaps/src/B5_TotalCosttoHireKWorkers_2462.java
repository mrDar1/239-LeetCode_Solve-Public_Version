import java.util.PriorityQueue;
/*problem:
//at original, written bad, here is better, in web discussion section has more explain.

given (int[] costs, int k, int candidates)
return total cost of hiring k workers - try to minimize cost as possible.

constraint: we cannot simply choose the cheapest worker!
        for simplicity: candidates==m!!!
we have 2 "windows" of workers only from there can choose worker!
1st window: first m workers from costs[],
2nd window: last  m workers from costs[].
each time we choose worker - we pull it out of costs[] - since "window" size static (size m),
its now added new member (to relevant window).

another constraint:
if both windows cheapest worker equal - choose the worker with smaller index!

constraint: candidates <= costs.length //less edge case checking.. */

/* 3 solution:
* 1st - 2 min-heap each with 1 value (lowest cost).
* 2nd - same as 1st but with: 1 min heap of 2 values: [lowest cost,0/1 == index of head/tail group] */

//1st - 2 min pq.
public class B5_TotalCosttoHireKWorkers_2462 {
    /*psudo:
     * constraint: each time cannot choose cheapest worker! only the cheap among 2 windows.
     * 1st - build 2 min-heap - to prioritize the cheapest worker inside each window.
     * 2nd - choose the cheapest worker of them both (with poll so its also delete from window).
     * 3rd - add new element to window - only when is not penetrate other window "territory" */
    public long totalCost(int[] costs, int k, int candidates) {
        int len = costs.length;
        long ans = 0;
        int headptr = candidates;
        int tailptr = len - 1 - candidates;
        //        min heap to choose from (must choose from one of them not cheapest in entire costs[]!! ):
        PriorityQueue<Integer> headpq = new PriorityQueue<>();
        PriorityQueue<Integer> tailpq = new PriorityQueue<>();

//        populate both pq:
        for (int i = 0; i < candidates; i++) {
            headpq.offer(costs[i]);
        }
        for (int i = Math.max(candidates, len - candidates); i < len; i++) {
            tailpq.offer(costs[i]);
        }

        for (int i = 0; i < k; i++) {
            if (    tailpq.isEmpty() ||
                    !headpq.isEmpty() && headpq.peek() <= tailpq.peek()) {
                ans += headpq.poll();


                if (headptr <= tailptr) {// only refill headptr if not penetrate tailptr window.
                    headpq.offer(costs[headptr++]);
                }

            } else {
                ans += tailpq.poll();

                if (headptr <= tailptr) { // only refill tailptr if not penetrate headptr window.
                    tailpq.offer(costs[tailptr--]);
                }
            }
        }
//        n==costs.size()
//        m==candidates
//        time: Ok+m log m : O2m(log m) create 2 heaps, (k*2*log m) poll smallest and then insert next element,for number of k workers.
//        space: O2m - size of 2 windows for head,tail candidates.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd
class B5_TotalCosttoHireKWorkers_2462_1_min_heap {
    public long totalCost(int[] costs, int k, int candidates) {
//         "heap sort", first prioritize lowest cost, if equal - small indices first.
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

//        heap, will use to both windows, to know difference we use other ID:
//        [lowest cost, head group == 0]
//        [lowest cost, tail group == 1]
        for (int i = 0; i < candidates; i++) { //populate head group
            heap.offer(new int[]{costs[i], 0});
        }
        for (int i = Math.max(candidates, costs.length - candidates); i < costs.length; i++) { //populate tail group.
            heap.offer(new int[]{costs[i], 1});
        }

        long answer = 0;
        int nextHead = candidates;
        int nextTail = costs.length - 1 - candidates;

        for (int i = 0; i < k; i++) {
            int[] curWorker = heap.poll(); //we initlize heap in manner that always poll smallest.
            int curCost      = curWorker[0];
            int curSectionId = curWorker[1];
            answer += curCost;

//            refill group that we just poll from - only if didnt penetrate other window.
            if (nextHead <= nextTail) {
                if (curSectionId == 0) {
                    heap.offer(new int[]{costs[nextHead], 0});
                    nextHead++;
                } else {
                    heap.offer(new int[]{costs[nextTail], 1});
                    nextTail--;
                }
            }
        }
        return answer;
    }
}