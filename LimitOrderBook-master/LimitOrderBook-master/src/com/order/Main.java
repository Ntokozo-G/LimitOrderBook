package com.order;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());
        Order order5 = new Order(uniqueId.getId(), 97.0, "bid", 50, LocalDateTime.now());
        Order order6 = new Order(uniqueId.getId(), 14.0, "offer", 60, LocalDateTime.now());
        Order order7 = new Order(uniqueId.getId(), 101.0, "bid", 60, LocalDateTime.now());
        Order order8 = new Order(uniqueId.getId(), 100, "bid", 100, LocalDateTime.now());
        Order order9 = new Order(uniqueId.getId(), 12.0, "offer", 10, LocalDateTime.now());
        Order order10 = new Order(uniqueId.getId(), 12.0, "offer", 100, LocalDateTime.now());


        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);
        orderBook.addOrder(order6);
        orderBook.addOrder(order7);
        orderBook.addOrder(order8);
        orderBook.addOrder(order9);
        orderBook.addOrder(order10);

        System.out.println("Added orders in the orderbook:");
        System.out.println("Added orders in the orderbook: " + OrderSide.BID.label);
        List<Order> bidList = orderBook.getOrders(OrderSide.BID.label);
        bidList.stream().map(e -> " "+e.getId()+"  "+e.getPrice()+"  "+e.getSide()+"  "+e.getSize()+"  "+ e.getTimestamp()).forEach(System.out::println);

        System.out.println("Added orders in the orderbook: " + OrderSide.OFFER.label);
        List<Order> offerList = orderBook.getOrders(OrderSide.OFFER.label);
        offerList.stream().map(e -> "  "+e.getId()+"  "+e.getPrice()+"  "+e.getSide()+"  "+e.getSize()+"  "+ e.getTimestamp()).forEach(System.out::println);


        System.out.println("Orderbook bid after modification:");
        orderBook.modifyOrder(order8.getId(), 50);

        List<Order> bidListM = orderBook.getOrders(OrderSide.BID.label);
        bidListM.stream().map(e -> " "+e.getId()+"  "+e.getPrice()+"  "+e.getSide()+"  "+e.getSize() +"  "+ e.getTimestamp()).forEach(System.out::println);

        System.out.println("Orderbook offer after modification:");
        orderBook.modifyOrder(order9.getId(), 30);
        List<Order> offerListM = orderBook.getOrders(OrderSide.OFFER.label);
        offerListM.stream().map(e -> " "+e.getId()+"  "+e.getPrice()+"  "+e.getSide()+"  "+e.getSize()+"  "+ e.getTimestamp()).forEach(System.out::println);

        System.out.println("Orderbook bid after modification:");
        orderBook.modifyOrder(order1.getId(), 70);

        List<Order> bidListMw = orderBook.getOrders(OrderSide.BID.label);
        bidListMw.stream().map(e -> " "+e.getId()+"  "+e.getPrice()+"  "+e.getSide()+"  "+e.getSize() +"  "+ e.getTimestamp()).forEach(System.out::println);


    }
}