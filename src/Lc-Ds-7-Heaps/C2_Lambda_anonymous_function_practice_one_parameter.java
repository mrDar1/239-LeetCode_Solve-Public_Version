public class C2_Lambda_anonymous_function_practice_one_parameter {

    static void one_parameter(Test2 t, Integer p){
        t.myprint(p);
    }

    public static void main(String[] args){
        one_parameter(param -> System.out.println(param), 10);
        //          can be replace by that line:
        //        one_parameter(System.out::println, 10);
    }
}

interface Test2 {
    void myprint(Integer p);
}