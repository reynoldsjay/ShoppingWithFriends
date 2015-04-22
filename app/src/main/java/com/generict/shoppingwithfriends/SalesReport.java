package com.generict.shoppingwithfriends;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class for holding products to be added to posts
 */
@ParseClassName("SalesReport")
class SalesReport extends ParseObject {
    /**
     * Constructor for sales report
     */
    public SalesReport() {
    }

    /**
     * Sets name
     * @param name of item to be reported
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Gets name
     * @return name of item reported
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Sets price
     * @param price of item to be reported
     */
    public void setPrice(int price) {
        put("price", price);
    }

    /**
     * Gets price
     * @return price of item to be reported
     */
    public int getPrice() {
        return getInt("price");
    }


    /*
     * Sets location
     * @param location of store that sells the item
     */
    public void setLocation(String location) { put("location", location); }

    /*
     * gets location
     * @param location of store that sells the item
     */
    public String getLocation() { return getString("location"); }
}
