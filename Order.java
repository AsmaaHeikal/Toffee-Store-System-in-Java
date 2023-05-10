import java.util.*;

public class Order {

    private OrderStatus status = OrderStatus.OPEN;
    private float orderTotal;
    private String deliveryAddress;
    private Payment payment;

    public Order(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean payOrder(Payment payment) {
        if (status == OrderStatus.CLOSED) {
            System.out.println("Order is already paid");
            return true;
        } else {
            boolean paidOrder = payment.settlePayment();
            if (paidOrder) {
                status = OrderStatus.CLOSED;
                System.out.println("Order is closed and delivered to the address you entered.\nThank you (:");
            }
            return paidOrder;
        }
    }
}
