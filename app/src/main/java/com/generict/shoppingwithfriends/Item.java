package com.generict.shoppingwithfriends;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class for holding products to be added to wish lists or posts
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    public Item() {
    }

    // getters and setters for name and price
    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public void setPrice(int price) {
        put("price", price);
    }

    public int getPrice() {
        return getInt("price");
    }
}
