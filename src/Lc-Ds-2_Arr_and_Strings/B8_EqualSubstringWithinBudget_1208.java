/*problem:
* got 2 strings, check if can make them equal within given cost ("maxCost").
* return 0 - if not possible
* it does possible - return cost.
* cost == ABS|Ascii value 1 - Ascii value 2| */

public class B8_EqualSubstringWithinBudget_1208 {
    public static void main(String[] args){
        String s = new String("abcd");
        String t = new String("cdef");
        int maxCost = 3;

        int n = equalSubstring(s,t,maxCost);
        System.out.println(n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int equalSubstring(String s, String t, int maxCost){
        int r = 0;
        int l = 0;
        int cur = 0; //current cost
        int max = 0; //max length so far

        for( ; r < s.length() ; ++r) {  // s/t - same length! Constraints!
            cur += Math.abs((int)s.charAt(r) - (int)t.charAt(r));
            while(cur > maxCost){
                cur -= Math.abs((int)s.charAt(l) - (int)t.charAt(l));
                ++l;
            }
            max = Math.max(max, r - l + 1);
        }
        //time: On - sliding window space: O1
        return max;
    }
}

//last time solve - pretty much the same..
// class Solution
// {
//     public int equalSubstring(String s, String t, int maxCost)
//     {
//         int r = 0;//right ptr
//         int l = 0;//lrft ptr
//         int sc = 0; //sum cost
//         int mlen = 0; //max length

//         //the next 2 lines is for use C syntax i like it better than java.
//         char[] cs = s.toCharArray();//char array s
//         char[] ct = t.toCharArray();//char array t

//         for( ; r < s.length() && r >= l; ++r)
//         {
//             sc += Math.abs( (int)ct[r] - (int)cs[r] );

//             while(maxCost - sc < 0)
//             {
//                 sc -= Math.abs( (int)ct[l] - (int)cs[l] );
//                 ++l;
//             }
//             mlen = Math.max(mlen, r-l+1);
//         }
//         return mlen;
//     }
// }