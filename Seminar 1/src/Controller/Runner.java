package Controller;
import model.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args)
    {
        Box box=new Box(
                new Apple(100),
                new Book(201),
                new Cake(199)
        );

        System.out.println(Arrays.toString(box.getHeavierThan200()));
    }
}
