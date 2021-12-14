import firstP.First;

public class MainApp {   //MainApp.java ---> MainApp.class

    public static void main(String[] v) {
//        for (int i=0; i<v.length; i++) {
//            int val= Integer.parseInt(v[i]);
//            System.out.println(val);
//        }

        First f= new First();
        f.main(v);
                                //we can't have access to the class Second because it is not public
    }
}


//Student s = new Student("ana", 100);
//System.out.println(s);    //if we have the method toString declared in the class Student