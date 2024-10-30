import java.util.Stack;
/*problem:
* list of peoples hight return int[] with how many people each 1 can see to its right only! */
/*intuition:
* traverse heights[] each time push index to stack.
* each time increase the ans at stack by 1 - until list is empty.
*
* the reason it will work is the stage before, where we have a sliding window:
* whenever we counter somone whose higher than cur - pop it from stack and moove to the one before.*/


public class B12_NumberofVisiblePeopleinQueue_1944 {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            int count = 0;

            // While stack is not empty and the current height is greater than the top of the stack
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                stack.pop();
                count++;
            }
            // If stack is not empty after the above, it means the current height can see one more person
            if (!stack.isEmpty()) {
                count++;
            }

            answer[i] = count;
            stack.push(i);
        }

        return answer;
    }
}
