import java.util.*;

/**
 * This class represents a cash payment.
 *
 * @author Asmaa Heikal
 * @version 1.0
 * @since 11 May 2023
 */
public class Cash extends Payment {
    /**
     * Amount of cash given
     */
    private float cash;
    /**
     * Amount of change due
     */
    private float change;

    /**
     * A constructor to intialize a cash object
     *
     * @param amount is the due amount to be paid from this cash
     * @param cash   is the cash given to be for the due amount
     */
    public Cash(float amount, float cash) {
        
        super(amount);
        this.cash = cash;
    }

    /**
     * Deduct the due amount from the given cash
     * and calculate the change.
     *
     * @return true if cash was enough to pay the due
     * and payment was successful
     */
    public boolean deductAmount() {
        
        change = cash - getAmount();
        return (change >= 0);
    }

    /**
     * Display a message saying if payment
     * was successful (cash larger than or equal due amount)
     * or not
     */
    public void displayMessage() {
        
        if (change >= 0) {
            System.out.println("You paid: " + cash + " and change: " + change);
            System.out.println("Successful Cash Payment ");
        } else {
            System.out.println("You paid: " + cash + " but required amount is: " + getAmount());
            System.out.println("Unsuccessful Cash Payment ");
        }
    }
}
