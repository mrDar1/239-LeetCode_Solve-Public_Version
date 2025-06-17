/*problem:
for each points[][] - return how many rectangle contain that point. */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B4_CountNumberofRectanglesContainingEachPoint_2250 {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        List<List<Integer>> heights = new ArrayList<>(); // list of lists to group rectangles by height
        for (int i = 0; i <= 100; i++) { //why 100? constraint.
            heights.add(new ArrayList<>());
        }

        // populate the lists with rectangle lengths
        for (int[] rectangle : rectangles) {
            heights.get(rectangle[1]).add(rectangle[0]);
        }

        // Sort the lists by length
        for (int i = 0; i <= 100; i++) {
            Collections.sort(heights.get(i));
        }

        int[] result = new int[points.length];

        // Process each point
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int count = 0;

            // For each height from y to 100, count rectangles that can contain the point
            for (int h = y; h <= 100; h++) {
                List<Integer> lengths = heights.get(h);
                if (!lengths.isEmpty()) {
                    // Use binary search to find the number of lengths >= x
                    int pos = Collections.binarySearch(lengths, x);
                    if (pos < 0) {
                        pos = -(pos + 1);
                    }
                    count += lengths.size() - pos;
                }
            }

            result[i] = count;
        }

        return result;
    }
}