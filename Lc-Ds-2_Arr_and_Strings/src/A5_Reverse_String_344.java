/*solutions:
 1st - using strinbuilder - will work only at java not c.
 2nd - in-method swap - work not pretty.
 3rd - out-method swap - learn here java pass by ref idea (Java Wrapper concept) (try to swap as in C with only 2 var without sending ref to char[] - and fail)
 4th - recursion - learned pretty way for tail recursion optimization.
 5th - fail-move by val... only work at LC not here.. (at Java only Wrapper move by ref, primitive do only at c dereference) */

public class A5_Reverse_String_344 {
    public static void main(String[] args){
    String s = new String("hello");
    Solution_A5_Reverse_String_344 obj_A5_Reverse_String_344 = new Solution_A5_Reverse_String_344();

    System.out.println("java strinbuilder: "+obj_A5_Reverse_String_344.reverseString_withStringBuilder(s.toCharArray()));
    System.out.println("inmethod swap:     "+obj_A5_Reverse_String_344.reverseString_withSwap_inmethod(s.toCharArray()));
    System.out.println("outmethod swap:    "+obj_A5_Reverse_String_344.reverseString_withSwap_outmethod(s.toCharArray()));
    System.out.println("tail recursion:    "+obj_A5_Reverse_String_344.reverseString_withTailRecursion(s.toCharArray()));
    System.out.println("original:          "+s );
    System.out.println("so far and the original string didnt change! that because in java work on primitive data-type, do not change the original!!");
    }
}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

// 1st - using strinbuilder - will work only at java not c.
class Solution_A5_Reverse_String_344 {
    //    only at java solution:
    public static String reverseString_withStringBuilder(char[] s) {
        StringBuilder sb = new StringBuilder();
        for (int len = s.length - 1; len >= 0; --len) {
            sb.append(s[len]);
        }
        return sb.toString();
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 2nd - in-method swap - work not pretty.
    public static String reverseString_withSwap_inmethod(char[] s) {
        int l = 0;
        int r = s.length - 1;
        for (; l < r; ++l, --r) {
            char t = s[l];
            s[l] = s[r];
            s[r] = t;
        }
        return new String(s);
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 3rd - out-method swap - learn here java pass by ref idea (Java Wrapper concept)(try to swap as in C with only 2 var without sending ref to char[] - and fail)
    public static String reverseString_withSwap_outmethod(char[] s) {
        int l = 0;
        int r = s.length - 1;
        for (; l < r; ++l, --r) {
            Swap(s, l, r);
        }
        return new String(s);
    }

    public static void Swap(char[] s, int l, int r) {
        char t = s[l];
        s[l] = s[r];
        s[r] = t;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 4th - recursion - learned pretty way for tail recursion optimization.
    public static String reverseString_withTailRecursion(char[] s) {
        helper(s, 0, s.length - 1);
        return new String (s);
    }
    public static void helper(char[] s, int left, int right){
//        recursion base case:
        if (left >= right)
            return;

        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;

//        note: its short way, equal to:
//        char tmp = s[left];
//        s[left] = s[right];
//        s[right] = tmp;
//
//        left++;
//        right--;

        helper(s, left, right);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

// 5th - fail-move by val... only work at LC not here.. (at Java only Wrapper move by ref, primitive do only at c dereference) */
//(work only at leetcode cause in java primitive data-dype do not pass by ref!)
//public void reverseString(char[] s){
//    int l = 0;
//    int r = s.length - 1;
//
//    for( ; l < r ; ++l, --r){
//        char t = s[l];
//        s[l] = s[r];
//        s[r] = t;
//    }
//}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

// 6th - solve again as 3rd - more elegant.
class Solution_A5_Reverse_String_344_second_time_solve {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;

        while (l < r){
            swap(s, l++ ,r--);
        }
    }

    private void swap(char[] s, int l, int r) {
        char t = s[l];
        s[l] = s[r];
        s[r] = t;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 7th - recursion - do again 4th - more elegant.
    public void reverseString_tail_recursion(char[] s) {
        helper(0,s.length - 1, s);
    }
    private void helper(int l, int r, char[] s) {
        if ( l >=r ){
            return;
        }

        char t = s[l];
        s[l] = s[r];
        s[r] = t;

        helper(l + 1,r - 1, s);
    }
}