import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) {


        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();
        Order order = new Order(uniqueId.getId(),100,10,"bid",LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(),100,20,"offer",LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(),100,10,"offer",LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(),200,10,"bid",LocalDateTime.now());

        //addOrders to the orderBook
        orderBook.addOrder(order);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3); 
        orderBook.addOrder(order4);

        ConcurrentLinkedQueue<Order> orders = orderBook.viewOrders();
        orders.stream().map(e -> " "+e.getOrderId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getQuantity()+" "+ e.getTimestamp()).forEach(System.out::println);


        // modifyOrders in the orderBook
        System.out.println("------------------------------------Modified Orders--------------------------------------------------");
        orderBook.modifyOrder(order.getOrderId(),30);

        ConcurrentLinkedQueue<Order> offerList = orderBook.viewOrders();
        offerList.stream().map(e -> " "+e.getOrderId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getQuantity()+" "+ e.getTimestamp()).forEach(System.out::println);

        // Delete Orders from the orderBook
        System.out.println("------------------------------------Delete Orders--------------------------------------------------");
        orderBook.deleteOrder(order3.getOrderId());
        System.out.println("Order has been deleted from the orderBook: "+ order3.getOrderId() +" " + order3.getPrice()+" "+ order3.getQuantity()+ " "+ order3.getSide() );
        ConcurrentLinkedQueue<Order> reducedList = orderBook.viewOrders();
        reducedList.stream().map(e -> " "+e.getOrderId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getQuantity()+" "+ e.getTimestamp()).forEach(System.out::println);

        Order order5 = new Order(uniqueId.getId(),350,15,"bid", LocalDateTime.now());
        orderBook.addOrder(order5);

        System.out.println("------------------------------------Delete Orders--------------------------------------------------");
        ConcurrentLinkedQueue<Order> addToList = orderBook.viewOrders();
        addToList.stream().map(e -> " "+e.getOrderId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getQuantity()+" "+ e.getTimestamp()).forEach(System.out::println);

        System.out.println("------------------------------------Modified Orders--------------------------------------------------");
        orderBook.modifyOrder(order.getOrderId(),5);

        ConcurrentLinkedQueue<Order> offList = orderBook.viewOrders();
        offList.stream().map(e -> " "+e.getOrderId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getQuantity()+" "+ e.getTimestamp()).forEach(System.out::println);


    }
}