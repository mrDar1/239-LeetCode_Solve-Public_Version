public class C3_Lambda_anonymous_function_practice_multi_parameters {

    static void multi_parameter(Test3 t, Integer p1, Integer p2){
        t.myprint(p1, p2);
    }

    public static void main(String[] args){
        multi_parameter((param1, param2) -> System.out.println(param1 + ", " + param2), 10, 20);
    }
}

interface Test3 {
    void myprint(Integer p1, Integer p2);
}