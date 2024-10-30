import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class C7_Lambda_example_GPT {
    public static void main(String[] args) {
        // Example 1: Lambda expression to represent a simple calculation
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("Addition: " + add.apply(5, 3)); // Output: Addition: 8

        // Example 2: Lambda expression with multiple lines of code
        Function<Integer, Integer> square = (num) -> {
            return num * num;
        };
        System.out.println("Square of 5: " + square.apply(5)); // Output: Square of 5: 25

        // Example 3: Lambda expression as a predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10)); // Output: Is 10 even? true

        // Example 4: Lambda expression in a forEach loop
        String[] names = {"Alice", "Bob", "Charlie", "David"};
        System.out.print("Names: ");
        for (String name : names) {
            Consumer<String> printName = (n) -> System.out.print(n + " ");
            printName.accept(name);
        }
        // Output: Names: Alice Bob Charlie David
    }
}