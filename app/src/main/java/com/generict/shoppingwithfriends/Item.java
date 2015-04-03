package com.generict.shoppingwithfriends;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Class for holding products to be added to wish lists
 */
@ParseClassName("Item")
class Item extends ParseObject {

    /**
     * Constructor for item
     * Left empty for now
     */
    public Item() {
    }

    /**
     * Sets name
     * @param name of item
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Gets name
     * @return string representation of name
     * @throws ParseException
     */
    public String getName() throws ParseException {
        return fetchIfNeeded().getString("name");
    }

    /**
     * Sets price
     * @param price of item
     */
    public void setPrice(int price) {
        put("price", price);
    }

    /**
     * Gets price
     * @return price of item
     * @throws ParseException
     */
    public int getPrice() throws ParseException {
            return fetchIfNeeded().getInt("price");
    }
}
