package firstP;

public class First {

    public static void main(String[] v) {
        for (int i=0; i<v.length; i++) {
            int val= Integer.parseInt(v[i]);
            System.out.println(val);
        }
    }
}
                                                    //just 1 class can be public in a single file
class Second {

    public static void main(String[] v) {
        for (int i=0; i<v.length; i++) {
            int val= Integer.parseInt(v[i]);
            System.out.println(val);
        }
    }
}
