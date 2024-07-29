package com.retailstorediscount.model;

import java.util.List;

public class Bill {
    private User user;
    private List<Item> items;

    public Bill(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}