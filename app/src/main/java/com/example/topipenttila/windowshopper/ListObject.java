package com.example.topipenttila.windowshopper;

import android.net.Uri;

import java.net.URL;
import java.util.List;

/**
 * Created by topipenttila on 04/03/17.
 */

public class ListObject {
    public String name;
    public String description;
    public String price;
    public String store;
    public String finderString;

    public ListObject(String name, String description, String price, String store) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.store = store;
        finderString = name + description + price;
        }

    public ListObject(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
