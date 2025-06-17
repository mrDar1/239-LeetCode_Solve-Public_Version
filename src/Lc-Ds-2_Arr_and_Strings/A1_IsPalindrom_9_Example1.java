/*problem: return true if given number or String is palindrome */

/*solution:
*use function overloading:
* 1st-input given as String.
* 2nd-input given as int and first cast it to string. */

public class A1_IsPalindrom_9_Example1 {
    public static void main(String[] args) {
        Solution_A1_IsPalindrom_Example1 obj_example1_ispalindrom = new Solution_A1_IsPalindrom_Example1();
//        int[] arr = {123, 1221, 87778, 123456};
        String [] mystr = {"aabbbaa", "aaa", "abaa", "abcdefg"};
        int[] arr = {12,121, 9911199};


        System.out.println("string answers:");
        for (String i : mystr){
            System.out.println(obj_example1_ispalindrom.IsPalindrom(i));
        }

        System.out.println("number answers:");
        for (int i : arr){
            System.out.println(obj_example1_ispalindrom.IsPalindrom(i));
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A1_IsPalindrom_Example1 {
    public boolean IsPalindrom(String str) {
        int l = 0; //left ptr
        int r = str.length() - 1;

        for (; l < r; ++l, --r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
        }
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public boolean IsPalindrom(int x) {
        String str = Integer.toString(x);

        int l = 0; //left ptr
        int r = str.length() - 1;

        for (; l < r; ++l, --r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
        }
        return true;
    }

//    time: On
//    space: O1
}