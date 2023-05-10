import java.util.*;

public class Cash extends Payment {
    private float cash;
    private float change;

    public Cash(float amount, float cash) {
        super(amount);
        this.cash = cash;
    }

    public boolean deductAmount() {
        change = cash - getAmount();
        return (change >= 0);
    }

    public void displayMessage() {
        if (change >= 0) {
            System.out.println("You paid: " + cash + " and change: " + change);
            System.out.println("Successful Cash Payment");
        } else {
            System.out.println("You paid: " + cash + " but required amount is: " + getAmount());
            System.out.println("Unsuccessful Cash Payment");
        }
    }
}
