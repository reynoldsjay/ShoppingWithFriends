package com.generict.shoppingwithfriends;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Class for holding products to be added to wish lists
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    public Item() {
    }

    // getters and setters for name and price
    public void setName(String name) {
        put("name", name);
    }

    public String getName() throws ParseException {
        return fetchIfNeeded().getString("name");
    }

    public void setPrice(int price) {
        put("price", price);
    }

    public int getPrice() throws ParseException {
            return fetchIfNeeded().getInt("price");
    }
}
