/*problem:
* given String - whenever got adjacent duplicate char - remove both of them */

public class A2_Remoove_all_adjacent_Duplicates_1047 {
    public static void main(String[] args)
    {
        String s = "abbaca";
        String s2 = "azxxzy";
        Solution_Remoove_all_adjacent_Duplicates_1047 obj_1047 = new Solution_Remoove_all_adjacent_Duplicates_1047();

        System.out.println(obj_1047.removeDuplicates(s));
        System.out.println(obj_1047.removeDuplicates(s2));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* traverse s and save each letter in sb.
* before save in sb check if sb == current s - if does remove last from sb*/
class Solution_Remoove_all_adjacent_Duplicates_1047 {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()){
            if (sb.length() > 0 && c == sb.charAt(sb.length() - 1) ){
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
//        time: O1n
//        space: O1n-if got no duplicate adjacent.
        return sb.toString();
    }
}