import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/*problem:
* given input List< from, to >
* return the destination city - that has no outgoing flight from!
* constraint: no loops! - much easier
* if not found return empty "". */

public class B2_DestinationCity_1436 {
    public static void main(String[] args){
        List<List<String>> s =  Arrays.asList(
                Arrays.asList("London", "New York"),
                Arrays.asList("New York", "Lima"),
                Arrays.asList("Lima", "Sao Paulo")
        );
//        debugging check:
//        System.out.println(s);

        System.out.println(destCity(s));
    }

    /*psudo: hashset of outgoing: traverse List and each time add the start city.
    * second traverse List destinations each time - and if not contains at outgoing - its destination!
    * at end if not found return "" */
    public static String destCity(List<List<String>> paths) {
        HashSet<String> has_outer_flight = new HashSet<>(); //if we see out flight from that city.

        for (int i = 0 ; i < paths.size() ; i++) {
            has_outer_flight.add(paths.get(i).get(0)); //what ever city in that set - cannot be the destination!
        }

        for (int i = 0 ; i < paths.size() ; i++) {
            String candidate = (paths.get(i).get(1)); //will give us the sequental order of the outflight

            if ( !has_outer_flight.contains(candidate)){
                return candidate;
            }
        }

//        time: O2n - 1st save outgoing citys 2nd-traverse and compare the destination citys
//        space: O1n
        return "";
    }
}