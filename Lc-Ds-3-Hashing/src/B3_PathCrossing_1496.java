import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B3_PathCrossing_1496 {
    public static void main(String[] args) {
        String s = "NES";

        boolean n = isPathCrossing(s);
        System.out.println(n);
    }

    /*psudo: create hashset of locations <List<x,y>>
    * each time check if contain that value - if it is return true - at end return false.*/
    public static boolean isPathCrossing(String path) {
    int x = 0;
    int y = 0;
    Set<List<Integer>> set = new HashSet<>(); // set can only appear once, and with list we can use multiple values.

    set.add( Arrays.asList(x,y) ); //adding start point

    for (Character c : path.toCharArray()){
        if (c == 'N'){
            ++y;
        }
        else if (c == 'S'){
            --y;
        }
        else if (c == 'E'){
            ++x;
        }
        else if (c == 'W'){
            --x;
        }

        if (set.contains(Arrays.asList(x,y))){
            return true;
        }else {
            set.add( Arrays.asList(x,y));
        }
    }

        //time & space: O1n
        return false;
    }
}