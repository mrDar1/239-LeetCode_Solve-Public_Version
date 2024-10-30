import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class C8_override_same_example {
    public static void main(String[] args) {
        // Example 1: BinaryOperator to add two integers
        BinaryOperator<Integer> add = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        System.out.println("Addition: " + add.apply(5, 3)); // Output: Addition: 8

        // Example 2: Function to square a number
        Function<Integer, Integer> square = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer num) {
                int result = num * num;
                return result;
            }
        };
        System.out.println("Square of 5: " + square.apply(5)); // Output: Square of 5: 25

        // Example 3: Predicate to check if a number is even
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return n % 2 == 0;
            }
        };
        System.out.println("Is 10 even? " + isEven.test(10)); // Output: Is 10 even? true

        // Example 4: Consumer to print names
        String[] names = {"Alice", "Bob", "Charlie", "David"};
        Consumer<String> printName = new Consumer<String>() {
            @Override
            public void accept(String n) {
                System.out.print(n + " ");
            }
        };
        System.out.print("Names: ");
        for (String name : names) {
            printName.accept(name);
        }
        // Output: Names: Alice Bob Charlie David
    }
}
