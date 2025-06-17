import java.util.HashMap;

/*problem:
return the max points, can be made:
given int[i][j]:
i==how many points will get.
j==how much "recovery time" (=skip) would have to do after. */

/*solutions:
* 1st - top down
* 2nd - bottom up
* 3rd - same as 2nd, different shorter write */

/*intuition:
* function dp return: max score with that index.
* we start from the top(=0) - DFS until end point and each time walk back towards the start, return the max path.
* recurrence relations:
*   what more prophet - choose curr and wait, or choose next.*/
public class A5_SolvingQuestionsWithBrainpower_2140 {
    HashMap<Integer, Long> memo; //<index, MaxPoints to that index-from the end!!>
    int[][] questions;

    public long mostPoints(int[][] questions) { //start here
        this.questions = questions;
        memo = new HashMap<>();
//        time: O1n
//        space:O1n
        return dp(0);
    }

    private long dp(int i) {
        if (i >= questions.length){
            return 0;
        }

        if (memo.containsKey(i)){
            return memo.get(i);
        }

        int curPoints = questions[i][0]; //points earned if choose to solve cur.
        int curSkip   = questions[i][1]; //how long the wait time if choose to solve cur (=how much element need to skip)
        int skipOffset= i + curSkip + 1; //to calculate how many elements must skip if choose solve cur: our current position (=i), plus curWait time (curSkip), plus +1, constraint: excluded count.

        memo.put(i, Math.max( curPoints + dp(skipOffset) , dp( i + 1) )); //recurrence relations: what more prophet - choose curr and wait, or choose next.
        return memo.get(i);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A5_SolvingQuestionsWithBrainpower_2140_tabulating {
    /*psudo:
    * use tabulating.
    * traverse int[][] backward!!
    * each time find max(choose: solve curr, or skip)
    * update each index in tabu[] to its max.
    * return tabu[] - as it find highest sums val from entire arr */
    public long mostPoints(int[][] questions) { //start here
        long[] tabu = new long[questions.length];

//        2 base cases-may as well delete them... optimized.
        if (questions.length == 1){
            return questions[0][0];
        }
        if (questions.length == 2){
            return Math.max(questions[0][0], questions[1][0]);
        }

        //the last val at tabu equal to origin as we cannot add nothing to it.
        tabu[questions.length - 1] = questions[questions.length - 1][0];


        for (int i = questions.length - 2; i >= 0; i--) {

            int curPoints = questions[i][0]; //points earned if choose to solve cur.
            int curSkip   = questions[i][1]; //how long the wait time if choose to solve cur (=how much element need to skip)
            int skipOffset= i + curSkip + 1; //to calculate how many elements must skip if choose solve cur: our current position (=i), plus curWait time (curSkip), plus +1, constraint: excluded count.
            tabu[i] = curPoints;

            if(skipOffset < questions.length){
//                start from end, so if after wait can solve another problem - add it.
                tabu[i] += tabu[(int)(skipOffset)];
            }

            // tabu[i] = max(<solve it, skip it>)
            tabu[i] = Math.max(tabu[i] , tabu[i+1] );
        }
//        time: O1n
//        space:O1n
        return tabu[0];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A5_SolvingQuestionsWithBrainpower_2140_tabulating_shorted_code {
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] tabu = new long[len + 1]; // len+1 to avoid out of bounds


        for (int i = len - 1; i >= 0; i--) {
            int curPoints = questions[i][0]; //points earned if choose to solve cur.
            int curSkip   = questions[i][1]; //how long the wait time if choose to solve cur (=how much element need to skip)
            int skipOffset= i + curSkip + 1; //to calculate how many elements must skip if choose solve cur: our current position (=i), plus curWait time (curSkip), plus +1, constraint: excluded count.

            // need to make sure we don't go out of bounds
            tabu[i] = Math.max(curPoints + tabu[Math.min(skipOffset, len)]  ,  tabu[i + 1]); //<choose max: skip, or not to skip>
        }

        return tabu[0];
    }
}