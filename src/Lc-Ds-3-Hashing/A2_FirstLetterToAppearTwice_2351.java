import java.util.*;
/*problem:
* return the first char that appears twice
* constraint: must be char that appear twice. */
public class A2_FirstLetterToAppearTwice_2351 {
    public static void main(String[] args){
        String s = new String("abcdd");
        char ch = repeatedCharacter(s);
        System.out.println(ch);
    }
    public static char repeatedCharacter(String s){
       Set<Character> duplicates = new HashSet<>();

       for(Character c : s.toCharArray()){
           if(duplicates.contains(c)){
               return c;
           }
           duplicates.add(c);
       }

        //time: On/1 - since all english letters are only 26.
        // space: Om. m=number of allowable characters in the input.

//        if got here and didnt find duplicates - return empty char
        return ' ';
    }
}

