package com.order;

public enum OrderSide {
    BID("bid"),
    OFFER("offer");

    public final String label;

    @Override
    public String toString() {
        return "OrderSide{" +
                "label='" + label + '\'' +
                '}';
    }

    private OrderSide(String label) {
        this.label = label;
    }
}
