import java.util.Stack;
/*problem
* given String with '*' in it - return String with no '*', each '*' delete char to it left. */

/*3 solution (all same, use stack with different DS: Stack,StringBuilder and cahr[]):
1st: using stack (and sb to reverse) - my second time intuitive way.
2nd: using only sb-as-stack - my last time intuitive way.
3rd: using char array - good for c language (using sb to convert from char[] to string).*/

public class B1_RemovingStarsFromString_2390 {
    public static void main(String[] args) {

        String s1 = "leet**cod*e"; //ouptut: lecoe
        String s2 = "erase*****"; //ouptut: ""

        Solution_RemovingStarsFromString_2390 obj_2390 = new Solution_RemovingStarsFromString_2390();

        System.out.println("1st solution:");
        System.out.println(obj_2390.removeStars(s1));
        System.out.println(obj_2390.removeStars(s2));

        System.out.println("2nd solution:");
        System.out.println(obj_2390.removeStars_with_sb(s1));
        System.out.println(obj_2390.removeStars_with_sb(s2));

        System.out.println("3rd solution:");
        System.out.println(obj_2390.removeStars_2_ptrs(s1));
        System.out.println(obj_2390.removeStars_2_ptrs(s2));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_RemovingStarsFromString_2390 {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();

//        building stack of character - after this block its the right answer but comas: "l,e,c,o,e" - how will we take them dowm? - using sb later
        for (Character c : s.toCharArray()) {
            if (!stack.isEmpty() && c == '*') {
                stack.pop();
            } else{
                stack.push(c);
            }
        }

//      moving stack answer to sb format for remooving coomas ','
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

//        time: O3n - stack, strinbuilder, reverse.
//        space: O1n stack, later Sb.
        return sb.reverse().toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public String removeStars_with_sb(String s) {
        StringBuilder sb = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (c != '*') {
                sb.append(c);
            } else if (sb.length() > 0) { // note: no need to write: c == '*' - since if got so far it must be true!
                sb.deleteCharAt(sb.length() - 1);
            }
        }
//        time: o1n
//        space: o1n
        return sb.toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public String removeStars_2_ptrs(String s) {
        char[] ch = new char[s.length()];
        int l = 0;
        int r = 0;

        for( ; r < s.length() ; ++r) {
            if (s.charAt(r) == '*' ) {
                --l;
            } else{
                ch[l++] = s.charAt(r);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < l ; ++i){
            sb.append(ch[i]);
        }
//        time: O2n - create char[], 2nd-traverse into string
//        space: on
        return sb.toString();
    }
}