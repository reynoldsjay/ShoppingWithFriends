package com.generict.shoppingwithfriends;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class for holding products to be added to wish lists or posts
 */
@ParseClassName("SalesReport")
public class SalesReport extends ParseObject {

    public SalesReport() {
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

    public void setLocation(String location) { put("location", location); }

    public String getLocation() { return getString("location"); }
}
