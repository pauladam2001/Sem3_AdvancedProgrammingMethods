package View;

import java.util.Arrays;
import java.util.Scanner;
import controller.Controller;
import model.Food;

public class ConsoleUI {
    private Controller controller;

    public ConsoleUI() {
        this.controller = new Controller(100);
    }

    private void displayList(Food[] data) {
//        if (data.length == 0) {
//            System.out.println("List is empty!");
//            return;
//        }
//        for (int i = 0; i < data.length; i++)           //OR for (Food datum : data) System.out.println(datum);
//            System.out.println(data[i]);
        int i = 0;
        while (data[i] != null && i < data.length) {
            System.out.println(data[i]);
            i++;
        }
    }

    public void start() {
        int option = -1;
        String optionString;
        Scanner myInput = new Scanner(System.in);

        while (true) {
            System.out.println("0. Exit;");
            System.out.println("1. Display list;");
            System.out.println("2. Add product;");
            System.out.println("3. Remove product;");
            System.out.println("4. Display products more expensive than 20 Ron/kg;");
            System.out.println("Your option: ");

            optionString = myInput.next();

            try {
                option = Integer.parseInt(optionString);
            } catch (Exception e) {
                System.out.println("Invalid option!");
//                continue;
            }

            if (option == 0) {
                System.out.println("See you later!");
                break;
            }

            else if (option == 1) {
                this.displayList(this.controller.getData());
            }

            else if (option == 2) {
                System.out.println("Type:");
                String foodType = myInput.next();
                System.out.println("Price:");
                try {
                    int foodPrice = myInput.nextInt();
                    this.controller.addController(foodPrice, foodType);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (option == 3) {
                System.out.println("Type:");
                String foodType = myInput.next();
                System.out.println("Price:");
                try {
                    int foodPrice = myInput.nextInt();
                    this.controller.removeController(foodPrice, foodType);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (option == 4) {
                this.displayList(this.controller.getProductsMoreExpensive());
            }

            else {
                System.out.println("Invalid option!");
            }
        }
        myInput.close();
    }
}
