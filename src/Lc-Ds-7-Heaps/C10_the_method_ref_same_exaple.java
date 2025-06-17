import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class C10_the_method_ref_same_exaple {
    public static void main(String[] args) {
        // Example 1: BinaryOperator
        BinaryOperator<Integer> add = new AddOperator()::apply;
        System.out.println("Addition: " + add.apply(5, 3)); // Output: Addition: 8

        // Example 2: Function
        Function<Integer, Integer> square = new SquareFunction()::apply;
        System.out.println("Square of 5: " + square.apply(5)); // Output: Square of 5: 25

        // Example 3: Predicate
        Predicate<Integer> isEven = new EvenPredicate()::test;
        System.out.println("Is 10 even? " + isEven.test(10)); // Output: Is 10 even? true

        // Example 4: Consumer to print names
        String[] names = {"Alice", "Bob", "Charlie", "David"};
        Consumer<String> printName = new PrintName()::accept;
        System.out.print("Names: ");
        for (String name : names) {
            printName.accept(name);
        }
        // Output: Names: Alice Bob Charlie David
    }


    // Example 1: BinaryOperator to add two integers
    static class AddOperator {
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    }

    // Example 2: Function to square a number
    static class SquareFunction {
        public Integer apply(Integer num) {
            int result = num * num;
            return result;
        }
    }

    // Example 3: Predicate to check if a number is even
    static class EvenPredicate {
        public boolean test(Integer n) {
            return n % 2 == 0;
        }
    }

    // Example 4: Consumer to print names
    static class PrintName {
        public void accept(String n) {
            System.out.print(n + " ");
        }
    }
}
