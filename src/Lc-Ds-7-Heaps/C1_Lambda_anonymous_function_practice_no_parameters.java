class C1_Lambda_anonymous_function_practice_no_parameters{
    static void no_paramater_print(NoParameter t) { t.myprint(); }

    public static void main(String[] args) {

        no_paramater_print (() -> System.out.println("no paramater lambda "));
    }
}
interface NoParameter {
    void myprint();
}
