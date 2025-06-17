/*problem:
* start at altitude 0 - return max altitude we reach.
* input given as net changes of height. */

public class B9_FindHIgestAltitude_1732 {
    public static void main(String[] args){
        int[] arr = {-5,1,5,0,-7};
        int k = 1;

        int n = largestAltitude(arr);
        System.out.println(n);
    }
    public static int largestAltitude(int[] gain){
        long t = 0; //total sum
        int max = 0;

        for(int i : gain){
            t += i;
            max = Math.max(max, (int)t);
        }
        //time: O1n
        //space: O1
        return max;
    }
}
