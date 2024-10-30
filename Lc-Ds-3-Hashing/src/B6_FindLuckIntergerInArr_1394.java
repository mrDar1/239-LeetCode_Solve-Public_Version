import java.util.*;
/*problem:
* Return the largest lucky integer in the array. If there is no lucky integer return -1
* lucky integer: freq==value */
public class B6_FindLuckIntergerInArr_1394 {
    public static void main(String[] args){
        int[] arr = {2,2,3,4};
        int n = findLucky(arr);
        System.out.println(n);
    }

    /*psudo:
    traverse and count occurrences in hashmap.
    * if key==val - check if max.
    * if max==0 return -1 else return its number. */
    public static int findLucky(int[] arr) {
        int lmax = 0; //lucky number maximum value.

        Map<Integer, Integer> map = new HashMap<>(); // <value, freq>
        for (int i : arr){//populate the map
            map.put(i, map.getOrDefault(i,0) + 1);
        }

        for (int i : map.keySet()){
            if (i == map.get(i)){ // if value == freq
                lmax = Math.max(lmax, i);
            }
        }
//        time: O2n - build count hash, find highest.
//        space: O1n
        return lmax = lmax == 0 ? -1 : lmax;
    }
}