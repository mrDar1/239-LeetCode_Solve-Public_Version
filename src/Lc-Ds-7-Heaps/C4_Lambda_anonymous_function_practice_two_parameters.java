public class C4_Lambda_anonymous_function_practice_two_parameters {
    public static void main(String[] args) {
        // The lambda expression here determines if one number is the factor of another
        NumericTest2 isFactor= (n,d) -> (n%d) == 0;
//          note - dont have to be same name wiil work here also:
//        NumericTest2 isFactor= (a,b) -> (a%b) == 0;

        if(isFactor.test(10,2))
            System.out.println("2 is the factor of 10");

        if(!isFactor.test(10,3))
            System.out.println("3 is not a factor of 10");

    }
}

interface NumericTest2{
    boolean test(int n, int d);
}