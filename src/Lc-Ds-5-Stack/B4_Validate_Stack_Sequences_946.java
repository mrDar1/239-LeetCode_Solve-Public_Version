import java.util.Stack;
/*problem:
* given 2 strings - can the other be a result of poped() from the first?
* return true/false */

public class B4_Validate_Stack_Sequences_946 {
    public static void main(String[] args) {
        Solution_B4_Validate_Stack_Sequences_946 obj_946 = new Solution_B4_Validate_Stack_Sequences_946();

        // Test case 1: Valid sequence
        int[] pushed1 = {1, 2, 3, 4, 5};
        int[] popped1 = {4, 5, 3, 2, 1};
        System.out.println("Test case 1: " + obj_946.validateStackSequences(pushed1, popped1));
        //desire output: true

        // Test case 2: Invalid sequence
        int[] pushed2 = {1, 2, 3, 4, 5};
        int[] popped2 = {4, 3, 5, 1, 2};
        System.out.println("Test case 2: " + obj_946.validateStackSequences(pushed2, popped2));
        //desire output: false
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
traverse "pushed[]" each time push to stack.
whenever we can poped according to "popped" - greedily do it, and count.
at end return if number of poped==len of "push[]"*/

class Solution_B4_Validate_Stack_Sequences_946{
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        int popnumbers = 0; //numbers of items that been poped
        Stack<Integer> stack = new Stack<>();

        for(int x : pushed){
            stack.push(x);

            while ( !stack.isEmpty() &&
                    popnumbers < len && // not mandatory. but i believe a good practice!
                    stack.peek() == popped[popnumbers]){
                stack.pop();
                ++popnumbers;
            }
        }
//            time:  O1n
//            space: O1n
        return popnumbers == len;
    }
}