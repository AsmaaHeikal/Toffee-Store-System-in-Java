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

        if (choice == 1) {
            System.out.println("--------------------Register--------------------");
            User register = new User();
            register.register();
        } else if (choice == 2) {
            System.out.println("--------------------Login--------------------");
            User user = new User();
            user.login();

            System.out.println("Do you want to display the catalog?");
            System.out.println("1-Yes   2-No");
            int choice1 = input.nextInt();

            if (choice1 == 1) {
                Catalog catalog = new Catalog();
                catalog.displayCatalog();
                int choice2 = 1;

                boolean firstTimeNo = true;
                while (choice2 == 1) {
                    System.out.println("Do you want to add items to your cart?");
                    System.out.println("1-Yes   2-No");

                    choice2 = input.nextInt();
                    ShoppingCart cart = new ShoppingCart(1);

                    if (choice2 == 1) {
                        firstTimeNo = false;
                        System.out.println("Enter the ID of the item you want to add:");
                        int id = input.nextInt();
                        if (id < 1 || id > 20) {
                            System.out.println("Invalid item ID!");
                            return;
                        }
                        System.out.println("Enter the quantity of the item:");
                        int quantity = input.nextInt();
                        cart = new ShoppingCart(id);

                        cart.addItem(id, quantity);
                        cart.displayCart();
                    } else if (choice2 == 2 && !firstTimeNo) {
                        System.out.println("\nPress 1 to pay for order or 2 to empty cart and exit");
                        int choice3 = input.nextInt();
                        if (choice3 == 1) {
                            System.out.println("--------------------Payment--------------------");
                            System.out.println("You want the order to be delivered to: \n1- your address\n2- another address");
                            int addressChoice = input.nextInt();
                            Order order;
                            if (addressChoice == 1) {
                                order = new Order(user.getAddress());
                            } else {
                                System.out.println("Enter the address you want the order to be delivered to:");
                                Scanner input1 = new Scanner(System.in);
                                String address = input1.nextLine();
                                order = new Order(address);
                            }
                            float amount = cart.getTotal();
                            System.out.println("Enter the amount of cash you want to pay with:");
                            float cash = input.nextFloat();
                            Payment payment = new Cash(amount, cash);
                            while (!order.payOrder(payment)) {
                                System.out.println("1- try again or 0- exit");
                                int choice4 = input.nextInt();
                                if (choice4 == 1) {
                                    System.out.println("Enter the amount of cash you want to pay with:");
                                    cash = input.nextFloat();
                                    payment = new Cash(amount, cash);
                                } else if (choice4 == 0) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice");
                                    System.exit(0);
                                }

                            }
                            cart.emptyCart();
                        } else if (choice3 == 2) {
                            cart.emptyCart();
                        } else {
                            System.out.println("Invalid choice");
                            cart.emptyCart();
                        }
                    } else if (choice2 == 2) {
                        System.out.println("Thank you for visiting our store!");
                    } else {
                        System.out.println("Invalid choice");
                        cart.emptyCart();
                    }
                }
            }
        } else if (choice == 3) {
            Catalog catalog = new Catalog();
            catalog.displayCatalog();
        } else {
            System.out.println("wrong choice");
        }
    }
}
