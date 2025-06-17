Bit manipulation - fastest way there is!! faster than regualr artihmatics:

notions:
bit/binary digit == 1 digit of 1/0
nibble 			 == four-bit digits: as 1111, 1100. (index as in array, count bits from 0,1,2,3....)
set a bit 		 == turn it on
clear a bit 	 == turn it off.
toggle /flip bit == flip  - if it was 1 - will be 0, if were 0 - will be 1
MSB /most significant bit == the leftmost
LSB /less significant bit == the rightmost

4 Basics:
1. & And
2. | Or
3. ^ Xor
4. << >> Shift (move over all bits):
        Shifts correspond to multipling or dividing a number by 2:
        Left  shift = multiply by 2 == 2^n. (n=right side of << )
        right shift = division by 2. right shift will "delete" LSB (rightmost bit).
5. ! / ~ not
________
Example:
int x = 15; // in binary, x = 1111
int y = 12  // in binary, y = 1100.

x | y = 1111 = 15

x & y = 1100 = 12

x ^ y = 0011 = 3

x << 1 = 11110 = 30 // multiply by 2.

1 << 5 = 32 == 1*2*2*2*2*2 - note how the first is the original, all the rest are *2.

5 << 3 = 40 == 5*2*2*2 - note how the first is the original, all the rest are *2.

10 << 3 = 80 == 10*2*2*2

2 << 3 = 16 == 2*2*2*2

x >> 1 = 111 = 7    // division by 2. (rememeber 15/2=7.5 , at C/Java will "floor division" to 7).


/* ************************** */

 Bitmask /Mask:
Bitmask == one bit flipped can be used to check individual bits of other numbers.

Example:
lets say we want to check if 3rd bit on:
"
	int x = 8;		//binary: 1000
	int mask = 1;
	boolean is_3rd_bit_on mask << 3 = 1 ? 1 : 0
"
//above code snippet will print true if the "x" bit is set.

 <Open intelij some great examples for masks!>

/* ************************** */

5. Bitwise "Not gate (~)" - simply toggle all bits:
int a = 5;  // 0101 (In Binary)
    a = ~a; // 1010 (In Binary ==10 in decimal)

note:
~ use to flip individual bits of an integer.
! use to flip the logical value of an expression. (true/false, 1/0)

/* ************************** */

use bitwise as "seen" hash:
usually done by hash, but can be optimized with bitwise:

use mask that the 'i' bit set, only if use arr[i].
to set that bit use - Xor (^) with mask.
to check if "seen" - use AND (&) with mask.

remember : can't do negative bit shifts, where can be negative use constant n to make sure that it doesn't go negative.
_________

bit manipulation operations have low priority (think PEMDAS/BEDMAS). Make sure to use parentheses () to very clearly define terms whenever using bit operations.


//from stanford - a lot of BitWise neat tricks! here used at (A9_HammingDistance_461)
http://graphics.stanford.edu/~seander/bithacks.html#CountBitsSetKernighan





/* ************************** */
Extras:
/* ************************** */

1’s Complement VS 2’s Complement - both are differenet ways to represent numbers in binary form:
1’s Complement - used at older computers (almost not in-use today).
2’s Complement - mostly common today.

"opposite / inverse numbers" == the opposite sign of number, as: [+1,-1] [+5,-5] [+10,-10]
at 1’s Complement - its easy just use "Not gate (~)".
at 2’s Complement - use "Not gate (~)" and do +1.

in 2's Complement have only 1 zero, and in 2's Complement - 2 zero!
for each different bit length the negative zero change place, headach for low-level programmers, scalability is much harder.

why is it so important to Extent numbers?
answer by examples:
-In 2014, Gangnam Style video overflowed YouTube view limit and it forced YouTube to upgrade view count from 32-bits to 64-bits signed integer.
-Similarly, 32-bit Unix clock will overflow on 19 Jan 2038 because it records time in seconds in a 32-bit signed integer.

top advantages of 2’s Complement:
	1. Unique Zero Representation (only 0000==0)
	2. simplify arithmetic (Addition, subtraction and more)
	3. Easy Sign Extension
	4. Direct Comparison
	5. Efficient Overflow Detection


/* ************************** */

Big-Endian & Little-Endian:
English reads from left to right, while Arabic reads from right to left. Endianness works similarly for computers.

"BE == Big-endian":  (dominant at network protocols, referred as network order)
the first byte (at the lowest memory address) is the MSB.

"LE == Little-endian":	most popular at modern computers!
The “little end” (the least significant part of the data LSB) comes first.
the first byte (at the lowest memory address) is the LSB.
