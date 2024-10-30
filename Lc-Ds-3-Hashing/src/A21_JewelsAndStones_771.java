import java.util.*;
public class A21_JewelsAndStones_771 {
    public static void main(String[] args){
        String jewels = new String("aA");
        String stones = new String("aAAbbbb");

        int n = numJewelsInStones(jewels, stones);
        System.out.println(n);
    }

//    psudo: hashmap for jewls.
//    traverse stones - if contain at jewls - ++c;
    public static int numJewelsInStones(String jewels, String stones) {
        HashSet <Character> jewlmap = new HashSet<>();
        int c = 0;

        for (Character ch : jewels.toCharArray()){
            jewlmap.add(ch);
        }

        for (Character ch : stones.toCharArray()){
            if(jewlmap.contains(ch)){
                ++c;
            }
        }
        // n-size of jewls,
        // m-size of stones.
        // time: On+m
        // space: On
        return c;
    }
}