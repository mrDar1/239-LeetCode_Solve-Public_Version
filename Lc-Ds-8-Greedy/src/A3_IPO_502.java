import java.util.Arrays;
import java.util.PriorityQueue;
/*problem:
return max money can make from at most "K" projects.
"w"==our current capital /money sum.
with that we may choose the most "profits[]" projects,
but, for each projects we first need to have at least "capital[]" money.

note - we do not "pay" for projects we just need to have that money, and can take the project.
note - costly project wont necessarily give more profit!!
note - can only take each project once! */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
1 - sort cost of each project and its profit - to do that in elegant way,
    merge profits[] and capital[] into one int[][], only then sort by the cost of the project.
2 - traverse sorted arr, anything that affordable - add to max-heap.
max-heap prioritize first the most profit projects!!
3 - take out the most profitable projects! until exhausted all possible projects(==k), or cannot afford next projects. */
public class A3_IPO_502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b - a ); //max-heap, store how much money will earn from take that project.

        //comfortable copy for sort, for each project hold: {project index} {cost, profit}
        int[][] projects = new int[profits.length][2];
        for (int i = 0; i < profits.length; i++) {
            projects[i] = new int[]{capital[i], profits[i]};
        }
//        note: above "for" can be write as:
//        for (int i = 0; i < profits.length; i++) {
//            projects[i][0] = capital[i];
//            projects[i][1] = profits[i];
//        }

        Arrays.sort(projects, (a,b) -> (a[0] - b[0]));  // increasing sort, only by the cost.
//        above sort can be replaced with:
//        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));

        int i = 0;//must initialize i before loop! why? if don't, may take same project several times - cannot do that by constraint!
        for (int j = 0 ; j < k ; ++j){ //constraint:  limited to k'th number of projects.
            for ( ; i < profits.length && w >= projects[i][0] ; i++) { //only added to heap - projects we can "afford" and have enough money.
                heap.add(projects[i][1]);
            }

            if (heap.peek() == null){ //if dont have enough money to buy any more project - return cur sum we have.
                return w;
            }

            w += heap.poll();
        }
//      time:  O (k+n) log n : o1n-build project arr, O n logn - sort. n* O 2*n log n - heap add==log n and remove==log k, up to n times.
//      space: On == O1n + log n : sort in java quicksort variant up to log n size, heap - up to n projects.
        return w;
    }
}