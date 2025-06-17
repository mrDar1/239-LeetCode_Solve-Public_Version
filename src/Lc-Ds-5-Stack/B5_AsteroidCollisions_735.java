import java.util.Arrays;
import java.util.Stack;
/*problem:
* return state of the asteroids after all collisions.
* positive == asteroid move right,
* negative == asteroid move left!!!
*
* note: all at same speed!*/

public class B5_AsteroidCollisions_735 {
    public static void main(String[] args){
        int[] arr1 = {5,10,-5};
        int[] arr2 = {8,-8};
        int[] arr3 = {10,2,-5};
        int[] arr4 = {-2,1,1,-1};

        Solution_AsteroidCollisions_735 obj_735 = new Solution_AsteroidCollisions_735();

        System.out.println(Arrays.toString(obj_735.asteroidCollision(arr1)));
        System.out.println(Arrays.toString(obj_735.asteroidCollision(arr2)));
        System.out.println(Arrays.toString(obj_735.asteroidCollision(arr3)));
        System.out.println(Arrays.toString(obj_735.asteroidCollision(arr4)));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_AsteroidCollisions_735 {
    /*psudo:
    * create "survival" stack.
    * traverse input arr: start with bool=true "is_cur_astorid_survive"-if he does, at end add it to stack.
    * while loop: 3 conditions (for astroid bo collide): peek mooving ->, cur moving <-, stack not empty.
    *
    * inside while loop another 3 conditions:
     * 1 - cur got crashed - continue.
     * 2 - cur == peek - bot explode so: pop from stack continue.
     * 3 - cur > peek - pop last asteroid and continue - maybe cur will crash more.
     *
     * if got this far(bool==true) - add cur to survival stack.
     *
     * ans cast: convert stack to int[] using for loop.
    * */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>(); //represent survival asteroids.

        for (int cur : asteroids){
            boolean is_cur_astorid_survive = true;

            while ( !st.empty() &&
                    cur < 0 &&      //if not move left - will never crash our last asteroid! - all will travel right!
                    st.peek() > 0){ //if not move right - will never crash our cur asteroid! - all will travel left!
                                    //if last move left and cur right - will not colide! just get more far from each other!


                int lastAsteroid = st.peek();

//                1st - cur got crashed - move to next asteroid.
                if (Math.abs(cur) < Math.abs(lastAsteroid) ){
                    is_cur_astorid_survive = false;
                    break;
                }

//                2nd - cur and peek destroy each other:
                if (Math.abs(cur) == Math.abs(lastAsteroid)){
                    is_cur_astorid_survive = false;
                    st.pop();
                    break;
                }

//                3rd - if cur crash prev - contiue at stack - maybe crash some more.
                if (Math.abs(cur) > Math.abs(lastAsteroid)){
                    st.pop();
                    continue;
                }

            }
            if (is_cur_astorid_survive){
                st.push(cur);
            }
        }

//        cast to ans format:
        int[] convert_stack_to_arr = new int[st.size()];
        for (int i = st.size() - 1; !st.empty() ; --i) {
            convert_stack_to_arr[i] = st.pop();
        }

//        time: O2n: O1-traverse and check what happen to cur; O1n-convert to int[]. (other words: O1n push to stack; O1n-pop.
//        space: On - at worst case when all astroid moove same direction == size of input.
        return convert_stack_to_arr;
    }
}