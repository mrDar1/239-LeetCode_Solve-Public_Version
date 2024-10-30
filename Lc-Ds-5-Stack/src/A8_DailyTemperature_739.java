import java.util.Arrays;
import java.util.Stack;

/* problem:
 from each day - we want to count how many days we got till next time its warmer.
 if dont have warmer day till end of array - put 0. */

/*2 solutions:
1st - monotonic stack (version of sliding window with stack)
2nd - optimized to O1 space. instead of stack use the answer[] as "memoization" */

public class A8_DailyTemperature_739 {
    public static void main(String[] args){
        int[] arr1 = {73,74,75,71,69,72,76,73};
//        desire output: [1,1,4,2,1,1,0,0]
//
        int[] arr2 = {30,40,50,60};
//        desire output: [1,1,1,0]

        int[] arr3 = {30,60,90};
//        desire output: [1,1,0]

        Solution_DailyTemperature_739 obj_739 = new Solution_DailyTemperature_739();

//        for practice - first time write the long way:
        int[] ans = new int[arr1.length];
        ans = obj_739.dailyTemperatures(arr1);
        System.out.println(Arrays.toString(ans));

//      now write the short way:
        System.out.println(Arrays.toString(obj_739.dailyTemperatures(arr2)));
        System.out.println(Arrays.toString(obj_739.dailyTemperatures(arr3)));

        System.out.println(" no hashmap: ");
        Solution_DailyTemperature_739_optimized obj739_optimized = new Solution_DailyTemperature_739_optimized();
        System.out.println(Arrays.toString(obj739_optimized.dailyTemperatures(arr1)));
        System.out.println(Arrays.toString(obj739_optimized.dailyTemperatures(arr2)));
        System.out.println(Arrays.toString(obj739_optimized.dailyTemperatures(arr3)));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


//using sliding windows and stack together!
class Solution_DailyTemperature_739 {
    public int[] dailyTemperatures(int[] temperatures){
        Stack<Integer> stack = new Stack<>(); //<hold the indexes only! not the temperatures>
        int[] answer = new int[temperatures.length];

        for (int currDay_index = 0 ; currDay_index < temperatures.length ; ++currDay_index)
        {
//            temperatures[stack.peek()] == will give us the temp value in that index.
//            temperatures[currDay_index]      == will give us the temp value in that index.
            while ( !stack.empty() && temperatures[stack.peek()] < temperatures[currDay_index] )
            {
                int prevDay_index = stack.pop();
                answer[prevDay_index] = currDay_index - prevDay_index;
            }

            stack.push(currDay_index);
        }

        //time: O1n - as sliding window!
        //space:O1n in worst can only store the entire input but no more!
        return answer;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_DailyTemperature_739_optimized {
    public int[] dailyTemperatures(int[] temperatures)
    {
        int n = temperatures.length;
        int hottest = 0;
        int[] answer = new int[n];

//        Arrays.fill(answer, 0); // no need in java but good practice.

        for (int currDay_index = n - 1; currDay_index >= 0; currDay_index--)
        {
            int currentTemp = temperatures[currDay_index];

            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue; // continue == answer[currDay_index]=0; since answer[] fill with 0.
            }

            int days = 1;
            while (temperatures[currDay_index + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day
                days += answer[currDay_index + days];
            }

            answer[currDay_index] = days;
        }

        return answer;
//        time: O1n amortized: O1n travese temperatures for end, whenever temp low - use answer "memoization"
//        space: O1 - do not count output arr.
    }
}