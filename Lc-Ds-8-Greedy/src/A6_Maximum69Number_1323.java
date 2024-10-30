import java.util.ArrayList;
import java.util.Map;
/*problem:
return biggest number possible.
given number of only 6,9 can flip 6 with 9 - one time only! */

//    intuition:  we want to put 9 as left as possible and 6 as right as possible.

/* solutions:
* 1st - use java built-in
* 2nd - ues StringBuilder
* 3rd -  math formula - to change the k digit from 6 to 9, add (3 * 10^K).
* 4th - as 3, with global recursion.
* 5th - as 3, no global recursion. */

public class A6_Maximum69Number_1323 {
    public static void main(String args[]){
        int num = 669;
        System.out.println(maximum69Number_java_built_in(num));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//1st
    public static int maximum69Number_java_built_in (int num) {
        String mystring = "" + num;
        return Integer.parseInt(mystring.replaceFirst("6", "9"));
        //before cast string to int, replace only the first 6, with 9.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//2nd:
    public int maximum69Number_use_Sb(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '6'){
                sb.setCharAt(i, (char)'9');
                break;
            }
        }
        return Integer.parseInt(sb.toString()); // cast sb to int.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*motivation:
* math formula: 3 * 10^k - to increase the k digit from the right.
* example: 969 + 30 = 999!!!
* 9669 + 300 == 9969 */

/*intuition:
* find k, k=='6' left_most location.
* after find, use formula.*/
//3rd:
    public int maximum69Number_math_formula(int num) {
        int numCopy = num;
        int currDigit = 0; //start from right digit and increase by 1 till reach leftmost 6 index.
        int i_leftmost_Six = -1; //index to the left most '6' digit.

//        when finish loop - "i_left_Six" would be updated and "numCopy" = 0.
        while (numCopy > 0) { //we cannot directly check leftmost digits. instead, search for '6' start from the right and advance.
            if (numCopy % 10 == 6){
                i_leftmost_Six = currDigit;
            }

            numCopy /= 10;
            currDigit++;
        }

//        if dont have any '6' - return original.
//        if do found 6 - use the formula to flip 6 to 9.
        return i_leftmost_Six == -1 ? num : num + 3 * (int)Math.pow(10, i_leftmost_Six);
    }

//    time:  O1L - L == number of digits in "num"
//    space: O1.

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    4th:
    int global_i_leftmost_Six = -1;
    public int maximum69Number_math_formula_recursion(int num) {
        int numCopy = num;
        int currDigit = 0; //start from right digit and increase so its 0.

        find_left_most_six(numCopy, 0);

        return global_i_leftmost_Six == -1 ? num : num + 3 * (int)Math.pow(10, global_i_leftmost_Six);
    }

    private void find_left_most_six(int n, int i) {
        if (n <= 0){
            return;
        }

        int reminder = n % 10;
        if (reminder == 6){
            this.global_i_leftmost_Six = i;
        }
        n /= 10;

        find_left_most_six(n, i+1);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    5th:
    public int maximum69Number_math_formula_recursion_no_global(int num) {
        int numCopy = num;
        int currDigit = 0; //start from right digit and increase so its 0.

        int i_leftmost_Six = find_left_most_six(numCopy, 0, -1);



        return i_leftmost_Six == -1 ? num : num + 3 * (int)Math.pow(10, i_leftmost_Six);
    }

    private int find_left_most_six(int n, int i, int i_leftmost_Six) {
        if (n <= 0){
            return i_leftmost_Six;
        }

        int reminder = n % 10;
        if (reminder == 6){
            i_leftmost_Six = i;
        }
        n /= 10;

        return find_left_most_six(n, i+1, i_leftmost_Six);
    }
}