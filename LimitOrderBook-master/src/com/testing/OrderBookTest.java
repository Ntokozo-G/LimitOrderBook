package com.testing;

import com.order.Order;
import com.order.OrderBook;
import com.order.OrderSide;
import com.order.UniqueIdGenerator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderBookTest {

    @Test
    public void testAddOrder() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.5, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());
        Order order5 = new Order(uniqueId.getId(), 97.0, "bid", 50, LocalDateTime.now());
        Order order6 = new Order(uniqueId.getId(), 14.0, "offer", 60, LocalDateTime.now());
        Order order7 = new Order(uniqueId.getId(), 12.0, "bid", 60, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);
        orderBook.addOrder(order6);
        orderBook.addOrder(order7);

        assertEquals(100.0, orderBook.getPrice("bid", 1), 0.01);
        assertEquals(99.5, orderBook.getPrice("bid", 2), 0.01);
        assertEquals(97.0, orderBook.getPrice("bid", 3), 0.01);
        assertEquals(12.0, orderBook.getPrice("bid", 4), 0.01);
        assertEquals(14.0, orderBook.getPrice("offer", 1), 0.01);
        assertEquals(100.0, orderBook.getPrice("offer", 2), 0.01);
        assertEquals(101.0, orderBook.getPrice("offer", 3), 0.01);
    }

    @Test
    public void testRemoveOrder() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, OrderSide.BID.label, 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, OrderSide.BID.label, 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, OrderSide.OFFER.label, 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, OrderSide.OFFER.label, 40, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        orderBook.removeOrder(order2.getId());


        assertEquals(100.0, orderBook.getPrice(OrderSide.BID.label, 1), 0.01);
        assertEquals(10.0, orderBook.getSize(OrderSide.BID.label, 1), 0.01);
        assertEquals(Double.NaN, orderBook.getPrice(OrderSide.BID.label, 2), 0.01);
        assertEquals(30.0, orderBook.getSize(OrderSide.OFFER.label, 1), 0.01);
        assertEquals(40.0, orderBook.getSize(OrderSide.OFFER.label, 2), 0.01);
    }

    @Test
    public void testModifyOrder() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        orderBook.modifyOrder(order1.getId(), 25);

        assertEquals(100.0, orderBook.getPrice("bid", 1), 0.01);
        assertEquals(25.0, orderBook.getSize("bid", 1), 0.01);

    }


    @Test
    public void testPrice() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(100.0, orderBook.getPrice("bid", 1), 0.01);
        assertEquals(99.0, orderBook.getPrice("bid", 2), 0.01);
        assertEquals(100.0, orderBook.getPrice("offer", 1), 0.01);
        assertEquals(101.0, orderBook.getPrice("offer", 2), 0.01);

    }

    @Test
    public void testSize() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(2, orderBook.getTotalSize("bid"), 0.01);
        assertEquals(2, orderBook.getTotalSize("offer"), 0.01);

    }

    @Test
    public void testAllOrders() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(2, orderBook.getOrders(OrderSide.BID.label).size(), 0.01);

        List<Order> bidList = orderBook.getOrders(OrderSide.BID.label);
        bidList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

        assertEquals(2, orderBook.getOrders(OrderSide.OFFER.label).size(), 0.01);

        List<Order> offerList = orderBook.getOrders(OrderSide.OFFER.label);
        offerList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

    }

    @Test
    public void testErrorHandling() {
        UniqueIdGenerator uniqueId = new UniqueIdGenerator();
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(uniqueId.getId(), 100.0, "bid", 10, LocalDateTime.now());
        Order order2 = new Order(uniqueId.getId(), 99.0, "bid", 20, LocalDateTime.now());
        Order order3 = new Order(uniqueId.getId(), 100.0, "offer", 30, LocalDateTime.now());
        Order order4 = new Order(uniqueId.getId(), 101.0, "offer", 40, LocalDateTime.now());
        Order order5 = new Order(uniqueId.getId(), 103.0, "offer", 50, LocalDateTime.now());

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);

        // test lowercase side characters being submitted
        assertEquals(100.0, orderBook.getPrice("BID", 1), 0.01);
        // test incorrect level number being submitted
        assertEquals(Double.NaN, orderBook.getPrice("bid", 3), 0.01);
        // test lowercase side characters being submitted
        assertEquals(10.0, orderBook.getSize("BID", 1), 0.01);
        // test incorrect level number being submitted
        assertEquals(0.0, orderBook.getSize("offer", 4), 0.01);
        // test lowercase side characters being submitted
        assertEquals(2, orderBook.getTotalSize("BID"), 0.01);
        // test lowercase side characters being submitted
        assertEquals(3, orderBook.getTotalSize("OFFER"), 0.01);
        // test incorrect side characters being submitted
        assertEquals(0.0, orderBook.getTotalSize("buy"), 0.01);
        // test incorrect side characters being submitted
        assertEquals(0.0, orderBook.getTotalSize("sell"), 0.01);
        // test random id
        assertEquals(103.0, orderBook.getPrice("offer", 3), 0.01);


    }
}
