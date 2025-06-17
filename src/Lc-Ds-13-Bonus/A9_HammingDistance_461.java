/*problem:
 * count how many different bits we have between given 2 numbers.
 * example: if both bits are 0/1 - do not count */

/*solutions:
 * 1st - java built-in function.
 * 2nd - classic BitWise
 * 3rd - as 2nd, portable to any data type size (work with nibble, char, int, long etc)
 * 3.5 - as 2nd, fail! be careful not to fall to that!
 * 4th - as 3rd, portable with no mask (decide by & 1).
 * 5th - as 4th, decide by LSB odd/even
 * 3rd - Brian Kernighan's Algorithm - optimize - skip all clear bits, and count just set bits. */

//1st - java built-in function
class Z4_HammingDistance_461_java_built_in_function {
    public int hammingDistance(int x, int y) {

//        time: O1 (the loop will be as long as 32 times - number of bits in INT)
//        space: O1 - no additional memory used.
        return Integer.bitCount(x^y);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd - classic BitWise
class A9_HammingDistance_461_mask {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        int xoredboth = x^y;
        int mask = 1;

        for (int i = 0; i < 31; i++) { //why 31? number of bits in Int are 32. and initialize to start from 1.
            if ((xoredboth & mask) != 0){
                ++sum;
            }
            mask <<= 1;
        }
//        time: O1 (the loop will be as long as 32 times - number of bits in INT)
//        space: O1 - no additional memory used.
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd - classic BitWise, portable - to work with any data-type size (nibble,char, short int, long, long long etc...)
class A9_HammingDistance_461_mask_portable {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        int xoredboth = x^y;
        int mask = 1;

        while (mask != 0){ //when Bitwise overflow - it toggle flag, and back to point 0. (here the cary/overflow flag not in use).
            if ((xoredboth & mask) != 0){
                ++sum;
            }
            mask <<= 1;
        }
//        time: O1 - the loop will be as long as bits in data type:
//                  4 iteration for nibble,
//                  8 iteration for char,
//                  16 iteration for int,
//                  etc....
//        space: O1 - no additional memory used.
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3.5 fail - be careful not to fall here
class A9_HammingDistance_461_fail {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        int xoredboth = x^y;
        int mask = 1;

        while (mask != 0){
            if ((xoredboth & mask) == 1){ //this line would make us fail!!!
                ++sum;
            }
            mask <<= 1;
        }
        return sum;
    }
}

/*why it fails?
assume work with 4-bits nibble:
our "xoredboth" = 15 (decimal) == 1111 (binary)

* every time mask will shift:
* 0001 & xoredboth = 1 correct!
* 0010 & xoredboth = 2 incorrect!
* 0100 & xoredboth = 4 incorrect!
* 1000 & xoredboth = 8 incorrect!

so, do not work with "==1" instead use "!=0". */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th - classic BitWise portable, and with no mask var.
public class A9_HammingDistance_461 {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        int xoredboth = x^y;

        while (xoredboth != 0){
            sum += (xoredboth & 1);
            xoredboth = xoredboth >> 1;
        }
//        time: O1 - the loop will be as long as bits in data type:
//                  4 iteration for nibble,
//                  8 iteration for char,
//                  16 iteration for int,
//                  etc....
//        space: O1 - no additional memory used.
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//5th - classic BitWise portable, and with no mask var - decide by pair/even.
class A9_HammingDistance_461_mask_portable_no_mask_play_with_odd_or_even {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        int xoredboth = x^y;

        while (xoredboth != 0){
            if (xoredboth % 2 == 1){
                ++sum;
            }
            xoredboth = xoredboth >> 1;
        }
//        time: O1 - the loop will be as long as bits in data type:
//                  4 iteration for nibble,
//                  8 iteration for char,
//                  16 iteration for int,
//                  etc....
//        space: O1 - no additional memory used.
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Z4_HammingDistance_461_Brian_krenighan_algorithm {
    public int hammingDistance(int x, int y) {
        int xoredboth = x ^ y;
        int sum = 0;

        while (xoredboth != 0) {
            sum += 1;
            // remove the rightmost bit of '1'
            xoredboth = xoredboth & (xoredboth - 1); // by doing & to number-1 the rightmost bit will be clear - so what we count is not the number but the number of times we repeated that process(of skiping 0, until reach 0).
        }
//        time: O1 - but faster - as it will skip the 0.
//        space: O1.
        return sum;
    }
}