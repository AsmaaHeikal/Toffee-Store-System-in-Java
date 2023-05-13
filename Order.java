import java.util.*;

/**
 * Order class represents an order made by a user
 * It has a status, total price, delivery address and payment
 * It can be paid for by cash or credit
 *
 * @author Asmaa Heikal
 * @version 1.0
 * @since 11 May 2023
 */
public class Order {

    private OrderStatus status = OrderStatus.OPEN;
    /**
     * total price of the order
     */
    private float orderTotal;
    /**
     * delivery address of the order
     */
    private String deliveryAddress;
    /**
     * payment method of the order
     */
    private Payment payment;

    /**
     * constructor requires tax rate applicable
     * to the order
     *
     * @param deliveryAddress the address where order is delivered
     */
    public Order(String deliveryAddress) {

        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Get the order status of this order
     *
     * @return an enumerated value OPEN or CLOSED
     * representing the order status.
     */
    public OrderStatus getStatus() {
        
        return status;
    }

    /**
     * pays the amount due for an order by cash or credit
     *
     * @param payment is payment made for the order
     * @return if order is paid or not
     */
    public boolean payOrder(Payment payment) {

        if (status == OrderStatus.CLOSED) {

            System.out.println("Order is already paid ");
            return true;
        } else {
            boolean paidOrder = payment.settlePayment();
            if (paidOrder) {
                status = OrderStatus.CLOSED;
                System.out.println("Order is closed and delivered to the address you entered.\nThank you!  (:");
            }

            return paidOrder;
        }
    }
}
