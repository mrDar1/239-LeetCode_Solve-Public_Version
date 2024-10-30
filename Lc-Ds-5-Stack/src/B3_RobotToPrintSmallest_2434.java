import java.util.ArrayDeque;
import java.util.Deque;
//didnt understand that question!
public class B3_RobotToPrintSmallest_2434 {
//    problem: sort from a-z using only 2 methods.

    public static void main(String[] args) {
        String s1 = "zza";

        System.out.println(robotWithString(s1));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


//    ezbuckeye - solution using dynamic programing
    public static String robotWithString(String s) {
        int len = s.length();
        int[] dp = new int[len]; // dp[i]: the index of smallest element in [i, len)

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();


        dp[len-1] = len-1;
        for (int i = len-2; i >= 0; i--) {
            dp[i] = s.charAt(i)<=s.charAt(dp[i+1]) ? i : dp[i+1];
        }
        int i = 0;

        while(i<len){
            if(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            if(stack.isEmpty() || s.charAt(dp[i])<stack.peek()){
                int smallestIndex = dp[i];
                for (; i <= smallestIndex; i++) {
                    stack.push(s.charAt(i));
                }
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

//        time: O1n
//        space: O3n: dp[] ,stack, sb
        return sb.toString();
    }
}