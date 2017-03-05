package com.example.topipenttila.windowshopper;

import android.net.Uri;

import java.net.URL;

/**
 * Created by topipenttila on 04/03/17.
 */

public class ListObject {
    public String name;
    public String description;
    public String price;
    public String finderString;

    public ListObject(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
        finderString = name + description + price;
        }
}
