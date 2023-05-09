import java.io.*;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("1- Register");
        System.out.println("2- Login");
        System.out.println("3- Display Catalog");
        System.out.print("enter your choice:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if(choice == 1){
            RegisterAndLogin register = new RegisterAndLogin();
            register.register();
        }


        else if(choice == 2){
            RegisterAndLogin login = new RegisterAndLogin();
            login.login();

            System.out.println("Do you want to display the catalog?");
            System.out.println("1-Yes   2-No");
            int choice1 = input.nextInt();

            if(choice1 == 1) {
                Catalog catalog = new Catalog();
                catalog.displayCatalog();
                int choice2 = 1;

            while(choice2 == 1) {
                System.out.println("Do you want to add items to your cart?");
                System.out.println("1-Yes   2-No");

                choice2 = input.nextInt();
                ShoppingCart cart = new ShoppingCart(1);

                if(choice2 == 1) {
                    System.out.println("Enter the ID of the item you want to add:");
                    int id = input.nextInt();
                    System.out.println("Enter the quantity of the item you want to add:");
                    int quantity = input.nextInt();
                    cart = new ShoppingCart(id);
                    cart.addItem(id, quantity);
                    cart.displayCart();
                }
                else if(choice2 == 2){
                    cart.emptyCart();
                }
                else{
                    System.out.println("Invalid choice");
                    cart.emptyCart();
        }
    }
}

        }

        else if(choice==3){
            Catalog catalog=new Catalog();
            catalog.displayCatalog();
        }
        
        else{
            System.out.println("wrong choice");
        }
    }
}
