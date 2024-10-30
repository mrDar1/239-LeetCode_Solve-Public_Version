/*problem: find max space - max hight and max distance from both "container walls".*/

/*psudo:
* travel with 2 ptr - l & r, until their even, each time compare space with max_so_far.
* each iteration advance l/r ptr - which smaller of them.
* return max. */
public class B9_ContainerWithMostWater_11 {
    public int maxArea(int[] height) {
        int ans = 0;
        int l = 0; //left ptr
        int r = height.length - 1;

        while (l < r){
            int cursize = (r - l) * Math.min(height[l], height[r]);

            ans = Math.max(ans, cursize);
            if (height[l] >= height[r]){
                --r;
            }else {
                ++l;
            }
        }
//        time: O1n
//        space:O1
        return ans;
    }
}