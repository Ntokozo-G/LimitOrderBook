package com.order;

import java.time.LocalDateTime;

public class Order {

    // TODO: The Order class could significantly be improved with the inclusion of a timestamp
    // variable to make it easier for order priority maybe

    private String id; // id of order
    private double price ;
    private String side; // B "Bid " or O " Offer"
    private long size ;
    private LocalDateTime timestamp;


    public Order(String id, double price, String side, long size, LocalDateTime timestamp) {
        this.id = id;
        this.price = price;
        this.side = side;
        this.size = size;
        this.timestamp = timestamp;
    }


    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    public long getSize() {
        return size;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    //TODO: having setters would make it easier to modify existing orders
    /*public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public void setSize(long size) {
        this.size = size;
    }*/

}