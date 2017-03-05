package com.example.topipenttila.windowshopper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by topipenttila on 04/03/17.
 */

public class DataStore {

    public ArrayList<ListObject> specials;
    public ArrayList<String> stores;
    public ArrayList<String> services;

    private static DataStore dataStore;
    /**
     * Create private constructor
     */
    private DataStore(){

    }
    /**
     * Create a static method to get instance.
     */
    public static DataStore getInstance(){
        if(dataStore == null){
            dataStore = new DataStore();
        }
        return dataStore;
    }


}
