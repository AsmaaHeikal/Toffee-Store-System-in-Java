import java.io.*;
import java.util.Scanner;

/**
 * This class to test Toffee Store core logic
 *
 * @author The phoenix team
 * @version 1.0
 * @since 11 May 2023
 */
public class main {

    /**
     * This is the main method.
     * It is for testing purposes to test
     * the work of Toffee Store program.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("1- Register");
        System.out.println("2- Login");
        System.out.println("3- Display Catalog");
        System.out.print("enter your choice:");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if (choice == 1) {
            //register
            System.out.println("--------------------Register--------------------");
            User register = new User();
            register.register();
        } else if (choice == 2) {
            //login and make order and payment
            MakeOrder makeorder = new MakeOrder();
            makeorder.makeOrder();
        } else if (choice == 3) {
            Catalog catalog = new Catalog();
            catalog.displayCatalog();
        } else {
            System.out.println("wrong choice");
        }
    }
}
