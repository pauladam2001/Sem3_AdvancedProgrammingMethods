public class Problem3 {
    public static void main(String[] v) {
       System.out.println(average(v) + " the result");
    }

    static float average(String[] v) {
        Integer sum = 0;
        Integer ct = 0;
//        float avg = 0;
        for (String s : v) {
            try {
                Integer ints = Integer.parseInt(s);
                sum += ints;
                ct++;
            } catch (NumberFormatException e) {
                System.out.println("Input string not an integer");
            }
        }
//        return avg = (float) sum/ct;
        return (float) sum/ct;
    }
}


//public class Student {
//    String name;
//    Integer nr;
//
//    public Student(String name, int nr) {
//        this.name = name;
//        this.nr = nr;
//    }
//
//    public String toString() {
//        return  name + " " + nr;
//    }
//}
