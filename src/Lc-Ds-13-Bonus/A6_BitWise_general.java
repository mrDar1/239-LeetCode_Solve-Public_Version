public class A6_BitWise_general {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}

//several popular bitwise "math formulas" uses:


//turn on specific bit.
//turn on == set a bit.
class SetSpecificBit {
    public static void main(String[] args) {
        int a = 5; // Binary: 0000 0101
        int pos = 1;
        int b = a | (1 << pos); // Set bit at position 1: 0000 0111, Decimal: 7

        System.out.println("Set bit at position 1: " + b); // Output: 7
    }
}

//turn off specific bit.
//turn-off == "clear a bit"
class ClearSpecificBit {
    public static void main(String[] args) {
        int a = 5; // Binary: 0000 0101
        int pos = 2;
        int b = a & ~(1 << pos); // Clear bit at position 2: 0000 0001, Decimal: 1

        System.out.println("Clear bit at position 2: " + b); // Output: 1
    }
}

//toggle == flip - if it was 1 - will be 0, if were 0 - will be 1.
class ToggleSpecificBit {
    public static void main(String[] args) {
        int a = 5; // Binary: 0000 0101
        int pos = 0;
        int b = a ^ (1 << pos); // Toggle bit at position 0: 0000 0100, Decimal: 4

        System.out.println("Toggle bit at position 0: " + b); // Output: 4
    }
}

//check if the n'th bit is on/off
class BitChecker {
    public static boolean isNthBitSet(int number, int n) {
        int mask = 1 << n;
        return (number & mask) != 0;
    }

    public static void main(String[] args) {
        int number = 5; // Binary: 0000 0101
        int n = 2;

        boolean result = isNthBitSet(number, 2);
        System.out.println("Is the " + n + "th bit set in " + number + "? " + result); // Output: true

        n = 1;
        result = isNthBitSet(number, 1);
        System.out.println("Is the " + n + "th bit set in " + number + "? " + result); // Output: false
    }
}