///**
// //official:
// * // This is ArrayReader's API interface.
// * // You should not implement it, or speculate about its implementation
// * interface ArrayReader {
// *     // Compares the sum of arr[l..r] with the sum of arr[x..y]
// *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
// *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
// *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
// *     public int compareSub(int l, int r, int x, int y) {}
// *
// *     // Returns the length of the array
// *     public int length() {}
// * }
// */
//
///*problem:
//* given int[] - all elements equal except for one.
//* return the index for the larger element.
//* cant use directly the  array but through "ArrayReader" */
//
//public class B5_FindtheIndexoftheLargeInteger_1533 {
//    public int getIndex(ArrayReader reader) {
//        int left = 0;
//        int len = reader.length();
//
//        while (len > 1) {
//            len /= 2;
//            // The left subarray starts from index left and the right part starts from left + length, both subarrays have length elements.
//            final int cmp = reader.compareSub(left, left + len - 1, left + len, left + len + len - 1);
//            if (cmp == 0) {
//                // The extra element is the larger integer.
//                return left + len + len;
//            }
//            if (cmp < 0) {
//                left += len;
//            }
//        }
////        time: O log n - calassic binary search.
////        space: O1n
//
//        return left;
//    }
//}
