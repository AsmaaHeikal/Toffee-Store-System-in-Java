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
