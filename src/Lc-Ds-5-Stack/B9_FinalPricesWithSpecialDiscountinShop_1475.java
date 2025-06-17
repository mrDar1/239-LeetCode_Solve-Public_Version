import java.util.Arrays;
import java.util.Stack;
/*problem:
* traverse "prices[]" - foreach element, find smaller value, locate to its right.
* the first element that found - is the discount.
* return int[] with new prices */

/*solutions:
* 1st - brute force
* 2nd - used stack. */
public class B9_FinalPricesWithSpecialDiscountinShop_1475 {
    public static void main(String[] args) {
        Solution_B9_FinalPricesWithSpecialDiscountinShop_1475 obj_1475 = new Solution_B9_FinalPricesWithSpecialDiscountinShop_1475();

        // Test cases
        int[] prices1 = {8, 4, 6, 2, 3};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {10, 1, 1, 6};

        // Expected output
        int[] expected1 = {4, 2, 4, 2, 3};
        int[] expected2 = {1, 2, 3, 4, 5};
        int[] expected3 = {9, 0, 1, 6};

        // Test cases
        int[] result1 = obj_1475.finalPrices(prices1);
        int[] result2 = obj_1475.finalPrices(prices2);
        int[] result3 = obj_1475.finalPrices(prices3);

        // Check if the results match the expected output
        System.out.println("Test Case 1: " + Arrays.equals(result1, expected1));
        System.out.println("Test Case 2: " + Arrays.equals(result2, expected2));
        System.out.println("Test Case 3: " + Arrays.equals(result3, expected3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B9_FinalPricesWithSpecialDiscountinShop_1475_use_array_brute_force {
    /*psudo:
    * for each price, traverse again n from i+1 until find smaller element, and decrease it */
    public int[] finalPrices(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int discount = 0;

            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }
            answer[i] = prices[i] - discount;
        }
//        time: On^2 - at worst case - until we find smaller element - inner loop traverse n-i.
//        space: O1 - not include output string.
        return answer;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_B9_FinalPricesWithSpecialDiscountinShop_1475{
    /*psudo:
    * traverse prices[]
    * each time store at stack the index of element to give discount to.
    * whenever find element that smaller than it - reduce it. */
    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>(); // keep track of indices of prices with pending discounts

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()] ) { //if there is something that cost less - to its right
                result[stack.pop()] -= prices[i];
            }
            stack.push(i);
            result[i] = prices[i];
        }
//        time: O1n
//        sace: O1 - for stack (not include output string)
        return result;
    }
}