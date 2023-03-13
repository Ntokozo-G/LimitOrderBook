package com.order;

import java.time.LocalDateTime;
import java.util.*;

public class OrderBook {
    private Map<String, List<Order>> ordersBySide;
    private Map<String, Order> ordersById;

    public OrderBook() {
        ordersBySide = new HashMap<>();
        ordersById = new HashMap<>();
    }

    // Add Order to OrderBook
    public void addOrder(Order order) {
        String side = order.getSide().toLowerCase();
        String id = order.getId();
        double price = order.getPrice();
        long size = order.getSize();
        int i = 0;

        List<Order> orders = ordersBySide.get(side);
        if ((Objects.equals(side, OrderSide.BID.label) || (Objects.equals(side, OrderSide.OFFER.label)))) {
            if (orders == null) {
                orders = new LinkedList<>();
                ordersBySide.put(side, orders);
            }

            if ((Objects.equals(side, OrderSide.BID.label))) {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() < price) {
                        break;
                    }
                }
                orders.add(i, order);
                ordersById.put(id, order);
            } else {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() > price) {
                        break;
                    }
                }
                orders.add(i, order);
                ordersById.put(id, order);
            }
        }
    }

    // Remove Order from OrderBook
    public void removeOrder(String id) {
        Order order = ordersById.get(id);
        if (order == null) {
            return;
        }

        String side = order.getSide();
        List<Order> orders = ordersBySide.get(side);
        if (orders == null) {
            return;
        }

        orders.remove(order);
        ordersById.remove(id);
    }

    // Modify Order in OrderBook
    public void modifyOrder(String id, long newSize) {
        Order order = ordersById.get(id);
        if (order == null) {
            return;
        }

        String side = order.getSide().toLowerCase();
        if ((Objects.equals(side, OrderSide.BID.label) || (Objects.equals(side, OrderSide.OFFER.label)))) {
            List<Order> orders = ordersBySide.get(side);
            if (orders == null) {
                return;
            }

            String modifiedSide = order.getSide();
            String modifiedId = order.getId();
            double modifiedPrice = order.getPrice();
            long modifiedSize = newSize;
            int i = 0;

            orders.remove(order);


            Order order1 = new Order(modifiedId, modifiedPrice, modifiedSide, modifiedSize, LocalDateTime.now());

//            String side1 = order1.getSide().toLowerCase();
//            int endOfList = orders.size();

            if ((Objects.equals(side, OrderSide.BID.label))) {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() < modifiedPrice) {
                        break;
                    }
//                    else if ((o.getPrice() == modifiedPrice)) {
//                        if(( o.getTimestamp().isAfter(order1.getTimestamp()))){
//
//                        }
//                        Swap(order1,order1);
//                        break;
//                    }

                }

                orders.add(i, order1);
                ordersById.put(id, order1);
            } else {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() > modifiedPrice) {
                        break;
                    }
                }
                orders.add(i, order1);
                ordersById.put(id, order1);
            }
            ordersBySide.put(modifiedSide, orders);
        } else {
            return;
        }

    }

    // Return Price based on Side (bid or offer and Level) from OrderBook
    public double getPrice(String side, int level) {
        if (side.toLowerCase().equals(OrderSide.BID.label) || side.toLowerCase().equals(OrderSide.OFFER.label)) {
            List<Order> orders = ordersBySide.get(side.toLowerCase());
            if (orders == null || orders.size() < level) {
                return Double.NaN;
            }
            return orders.get(level - 1).getPrice();
        } else {
            return Double.NaN;
        }
    }

    //Given a side and a level return the size for that level
    public long getSize(String side, int level) {
        if (side.toLowerCase().equals(OrderSide.BID.label) || side.toLowerCase().equals(OrderSide.OFFER.label)) {
            List<Order> orders = ordersBySide.get(side.toLowerCase());
            if (orders == null || orders.size() < level) {
                return 0;
            }
            return orders.get(level - 1).getSize();
        } else {
            return 0;
        }
    }

    // Return OrderBook Size based on Side ( bid or offer)
    public long getTotalSize(String side) {
        if (side.toLowerCase().equals(OrderSide.BID.label) || side.toLowerCase().equals(OrderSide.OFFER.label)) {
            List<Order> orders = ordersBySide.get(side.toLowerCase());
            if (orders == null) {
                return 0;
            }

            return orders.size();
        } else {
            return 0;
        }
    }

    // Return all the orders from that side (bid or offer and Level)  of the book, in level- and time-order based on Side
    public List<Order> getOrders(String side) {
        if ((side.toLowerCase()).equals(OrderSide.BID.label) || side.toLowerCase().equals(OrderSide.OFFER.label)) {
            List<Order> orders = ordersBySide.get(side.toLowerCase());
            if (orders == null) {
                return new LinkedList<>();
            }
            return new LinkedList<>(orders);
        } else {
            return new LinkedList<>();
        }
    }

    //TODO: Swap order positions based on the time timestamps or implement a better data structure maybe a Queue
    public void Swap (Order order1, Order order2){
        Order temp = order1;
        order1 = order2;
        order2 = temp;
    }
}

