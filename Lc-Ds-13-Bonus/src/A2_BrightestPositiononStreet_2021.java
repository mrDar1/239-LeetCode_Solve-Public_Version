import java.util.ArrayList;
import java.util.List;
//testing
public class A2_BrightestPositiononStreet_2021 {
    public static void main(String[] args) {
        solution_A2_BrightestPositiononStreet_2021_difference_arr obj_2021 = new solution_A2_BrightestPositiononStreet_2021_difference_arr();
        // Test Case 1
        int[][] lights1 = {{-3, 2}, {1, 2}, {3, 3}};
        System.out.println(obj_2021.brightestPosition(lights1)); // Output: -1
        // Test Case 2
        int[][] lights2 = {{1, 0}, {0, 1}};
        System.out.println(obj_2021.brightestPosition(lights2)); // Output: 1
        // Test Case 3
        int[][] lights3 = {{1, 2}};
        System.out.println(obj_2021.brightestPosition(lights3)); // Output: -1
        // Additional Test Cases
        int[][] lights4 = {{0, 1}, {10, 1}};
        System.out.println(obj_2021.brightestPosition(lights4)); // Output: -1
        int[][] lights5 = {{5, 2}, {5, 2}, {5, 2}};
        System.out.println(obj_2021.brightestPosition(lights5)); // Output: 3
        int[][] lights6 = {{1, 5}, {2, 3}, {4, 1}};
        System.out.println(obj_2021.brightestPosition(lights6)); // Output: 3
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*problem:
 * return the place with most different "streetlights" on it.
 * light[][] == <offset position, radius that light reach > note - both inclusive values!
 * for equal values - return smallest position.
 * constraint: street is extremely long - position <= 10^18  */

/*hint: since street so long - cannot do here bucket sort.
* what can one do? think about prefix sum when reduce space.  */

/*motivation:
 * do not mention lights different so all lights power == 1. they cast light longer but same power!!
 * since light is radius so: left==position-radius, right==position+radius */

/*solutions:
 * 1st-difference arr
 * 1.5-same, some practice of java 2D. */

class solution_A2_BrightestPositiononStreet_2021_difference_arr {
    /*psudo:
    * build a List of events[] <position, number of lights on it>
    * sort that list , then traverse list - each update position with most lights on.  */
    public int brightestPosition(int[][] lights) {
        List<int[]> lightCoverage = new ArrayList<>(); // <positions, how many lights cover it> hold only changes! (not entire street length, only when boundary's of each streetlight)

        for (int[] flash : lights){
            int position = flash[0]; //measured in offset.
            int coverage = flash[1]; //light coverage range

            lightCoverage.add(new int[] {position - coverage    , 1});  //where our cur light start flash.
            lightCoverage.add(new int[] {position + coverage + 1, -1}); //where our cur light end flash.
        }

        // Sort "lightCoverage" by position. in case of tie, start lightCoverage (+1), come before end lightCoverage (-1) - as constraint (all lights are included)
        lightCoverage.sort((a,b)-> a[0]==b[0] ? Integer.compare(a[1],b[1]) : Integer.compare(a[0],b[0]));
//        note - this line wont work - when sort, do not handle equal values!
//        Collections.sort(lightCoverage, (a, b) -> Integer.compare(a[0], b[0]));


        int maxNumberOfLights = 0; //max number of different streetlights.
        int maxPosition = 0;   // where max number of different streetlights - located/positioned.
        int timeStamp = 0; //traverse street changes - represent cur-total number of lights from different streetlights,
        // each time, add count for new streetlight, and reduce count when streetlight-coverage ends.
        // note - will say that again - reduce (-1) each time streetlight coverage ends. so can do "+=" and no need to "-=" since hold negative values.

        for (int[] tempArr : lightCoverage){
            int curPosition = tempArr[0];
            int curNumberOfLights = tempArr[1];
            timeStamp += curNumberOfLights;

            if (maxNumberOfLights < timeStamp){
                maxNumberOfLights = timeStamp;
                maxPosition = curPosition;
            }
        }
//        n==lights.len (=offset position)
//        m==lights[0].len (=radius that light reach(inclusive)).
//        time: O n log n + 2n == On log n:
//                      On log n - sort,
//                      1n-build prefix sum (timestamp),
//                      1n-find max in prefix.
//        space: Onm - build prefix sum
        return maxPosition;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class solution_A2_BrightestPositiononStreet_2021_difference_arr_practiceJava2D {
    public int brightestPosition(int[][] lights) {
        List<int[]> lightCoverage = new ArrayList<>(); // <positions, how many lights cover it> hold only changes! (not entire street length, only when boundary's of each streetlight)

        for (int[] flash : lights){
            int position = flash[0]; //measured in offset.
            int coverage = flash[1]; //light coverage range

            lightCoverage.add(new int[] {position - coverage    , 1});  //where our cur light start flash.
            lightCoverage.add(new int[] {position + coverage + 1, -1}); //where our cur light end flash.
        }

        lightCoverage.sort((a,b)-> a[0]==b[0] ? Integer.compare(a[1],b[1]) : Integer.compare(a[0],b[0]));

        int maxNumberOfLights = 0;
        int maxPosition = 0;
        int timeStamp = 0;

        for (int i = 0; i < lightCoverage.size(); i++) {
            int curPosition       = lightCoverage.get(i)[0];
            int curNumberOfLights = lightCoverage.get(i)[1];
            timeStamp += curNumberOfLights;

            if (maxNumberOfLights < timeStamp) {
                maxNumberOfLights = timeStamp;
                maxPosition = curPosition;
            }
        }
        return maxPosition;
    }
}