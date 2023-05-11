import java.util.Objects;
import java.util.regex.*;
import java.util.Scanner;
import java.io.*;

public class User {

    private String email;
    private String password;
    private String address;

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    public boolean isFoundEmail(String email) throws IOException {
        FileReader reader = new FileReader("Users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void register() throws IOException {
        System.out.print("enter your email:");
        Scanner input = new Scanner(System.in);
        email = input.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("enter a valid email:");
            email = input.nextLine();
        }
        if (isFoundEmail(email)) {
            System.out.println("this email is already registered");
            System.exit(0);
        }
        System.out.print("enter your password:");
        password = input.nextLine();
        while (!isStrongPassword(password)) {
            System.out.print("enter a strong password:");
            password = input.nextLine();
        }
        System.out.print("enter your address:");
        address = input.nextLine();

        OTP userOTP = new OTP();
        String OTP = userOTP.generateOTP();
        userOTP.sendOTP(email, OTP);
        System.out.print("To verify your email enter the OTP you received: ");
        String enteredOTP = input.nextLine();
        if (enteredOTP.equals(OTP)) {
            System.out.println("Your account has been created successfully");
        } else {
            System.out.println("Invalid OTP, try again later.");
            System.exit(0);
        }

        //store user data in database
        try {
            FileWriter writer = new FileWriter("Users.txt", true);
            writer.write(email + "\n" + password + "\n" + address + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() throws IOException {
        boolean isFoundEmail = false;
        while (!isFoundEmail) {
            System.out.print("enter your email:");
            Scanner input = new Scanner(System.in);
            email = input.nextLine();
            FileReader reader = new FileReader("Users.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(email)) {
                    isFoundEmail = true;
                    String storedPassword = bufferedReader.readLine();
                    int numberOfTries = 3;
                    while (numberOfTries-- > 0) {
                        System.out.print("enter your password:");
                        password = input.nextLine();

                        if (storedPassword.equals(password)) {
                            address = bufferedReader.readLine();
                            bufferedReader.close();
                            System.out.println("You have logged in successfully");
                            return;
                        }
                        if (numberOfTries != 0) {
                            System.out.println("Wrong password. Please try again.");
                        } else {
                            System.out.println("You have entered wrong password 3 times. Please try again later.");
                            System.exit(0);
                        }
                    }
                }
                // Skip the two next lines, which should contain the password and the address
                bufferedReader.readLine();
                bufferedReader.readLine();
            }
            System.out.println("Email not found. Press 1 to Try again or 0 to Exit.");
            String choice = input.nextLine();
            if (!choice.equals("1")) System.exit(0);
            bufferedReader.close();
        }

    }

    public String getAddress() {
        return address;
    }
}


