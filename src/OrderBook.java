import java.time.LocalDateTime;
import java.util.*;
import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class OrderBook {

    //I couldn't use a LinkedList since I face issues with concurrency , I could've implemented an iterator to solve the isssue
    //but decided to go with the ConcurrentLinkedList since I just wanted to write simple and clean code

    private Queue<Order> orders;
    private Map<String, Order> ordersById;

    public OrderBook() {
        orders = new ConcurrentLinkedQueue<>();
        ordersById = new HashMap<>();
    }

    public void addOrder(Order order) {
        String newOrderSide = order.getSide().toLowerCase();
        String newOrderId = order.getOrderId();
        double newOrderPrice = order.getPrice();
        long newOrderQuantity = order.getQuantity();

        if ((newOrderSide.toLowerCase()).equals(OrderSide.BID.label) || (newOrderSide.toLowerCase()).equals(OrderSide.OFFER.label)) {
            if (orders == null) {
                orders.add(order);
                ordersById.put(order.getOrderId(), order);
            }

            if (newOrderSide.equals(OrderSide.BID.name())) {
                for (Order existingOrder : orders) {
                    if (newOrderSide.equals(existingOrder.getSide()) && existingOrder.getPrice() > newOrderPrice) {
                        break;
                    }

                }
                orders.add(order);
                ordersById.put(newOrderId, order);
            } else {
                for (Order existingOrder : orders) {
                    if (newOrderSide.equals(existingOrder.getSide()) && existingOrder.getPrice() < newOrderPrice) {
                        break;

                    }
                }

                orders.add(order);
                ordersById.put(newOrderId, order);

            }


        }
    }

    public void deleteOrder(String orderId) {
        //Can also do checks if the order exists or not before trying to remove it
        Order order = ordersById.get(orderId);
        if (order == null) {
            return;
        }

        orders.remove(order);
        ordersById.remove(orderId);

    }

    public void modifyOrder(String orderId, long newQuantity) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                String modifiedId = order.getOrderId();
                double modifiedPrice = order.getPrice();
                long modifiedQuantity = newQuantity;
                String modSide = order.getSide();

                orders.remove(order);
                ordersById.remove(orderId);
                Order modifiedOrder = new Order(modifiedId, modifiedPrice, modifiedQuantity, modSide, LocalDateTime.now());

                orders.add(modifiedOrder);
                ordersById.put(modifiedId, modifiedOrder);
            } else {
                return;
            }


        }
    }

    public ConcurrentLinkedQueue<Order> viewOrders() {
        if (orders == null) {
            return new ConcurrentLinkedQueue<>();
        }
        return new ConcurrentLinkedQueue(orders);
    }


}
