import java.util.Arrays;
import java.util.PriorityQueue;
/*problem:
* return the max number of happy kids.
* each cookie weight s[].
* each child want to each at least g[] weight cookie. */

/*solutions:
* 1st - 2 min heap
* 2nd - sort of inputs */

public class B3_AssignCookies_455 {
    /*use min heap for size and greed.
    * then, iterate size and check if can give to some child. start with less greeding child*/
    public int findContentChildren(int[] g, int[] s) {
        PriorityQueue<Integer> greed = new PriorityQueue<>();
        PriorityQueue<Integer> cookie_weight = new PriorityQueue<>();
        int ans = 0;

        for (int i : g){
            greed.offer(i);
        }
        for (int i : s){
            cookie_weight.offer(i);
        }

        while ( !cookie_weight.isEmpty() && !greed.isEmpty()){
            int cur_coockie = cookie_weight.poll();

            if (cur_coockie >= greed.peek()){
                ++ans;
                greed.poll();
            }
        }
//        time: O1n+m, Om
//        space: O n + m (n==g , m==s)
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_AssignCookies_455_with_sort {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0; //kids that happy with size of their cookie.
        int cookieIndex = 0;

        while (cookieIndex < s.length && ans < g.length) {
            if (s[cookieIndex] >= g[ans]) {
                ans++;
            }
            cookieIndex++;
        }
        return ans;
    }
}