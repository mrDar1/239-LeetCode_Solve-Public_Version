/*problem:
* Given two strings s and t, return true if s is a subsequence of t, or false otherwise.*/

public class A4_IsSubsequence_392_Example4 {
    public static void main(String [] args){
        Solution_A4_IsSubsequence_392 obj_A4_IsSubsequence_392 = new Solution_A4_IsSubsequence_392();
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(obj_A4_IsSubsequence_392.isSubsequence(s,t));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A4_IsSubsequence_392 {
    public boolean isSubsequence(String s, String t) {
        int is = 0; //index of s
        int it = 0; //index of t

        while (is < s.length() && it < t.length()){
            if(s.charAt(is) == t.charAt(it)){
                ++is;
                ++it;
            }else {
                ++it;
            }
        }
        return is == s.length();
    }
//    time: On
//    space:O1
}