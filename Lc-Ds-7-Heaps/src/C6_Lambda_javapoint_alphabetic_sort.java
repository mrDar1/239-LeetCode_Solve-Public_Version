import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class C6_Lambda_javapoint_alphabetic_sort {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        fruits.add("Banana");
        fruits.add("Zineapple");
        fruits.add("Drape");
        fruits.add("Crange");
        fruits.add("Apple");

        // Sort the list of fruits alphabetically
        fruits.sort((f1, f2) -> f1.compareTo(f2));

//      note above line could be replaced with:
//        fruits.sort(Comparator.naturalOrder());
//        fruits.sort(String::compareTo);


        // Print the sorted list of fruits
        System.out.println("Sorted list of fruits:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}

