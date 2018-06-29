package com.example.amazinglu.in_app_search_demo;

import java.util.ArrayList;
import java.util.List;

public class StoreData {
    private static List<String> stores ;
    static {
        stores =  new ArrayList<String>();
        stores.add("Amazon");
        stores.add("Sears");
        stores.add("Ebay Home");
        stores.add("Macys Home");
        stores.add("JCpenney Kids");
        stores.add("Ebay Electronics");
        stores.add("Amazon Appliance");
        stores.add("Ebay Mobiles");
        stores.add("Ebay Kids");
        stores.add("Amazon Fashion");
        stores.add("Ebay Travel");
        stores.add("JCpenney Home");
        stores.add("JCpenney Luggage");
        stores.add("JCpenney Appliance");
        stores.add("JCpenney Fashion");
        stores.add("Amazon Luggage");
        stores.add("Macys Jewellery");
        stores.add("JCpenney Jewellery");
        stores.add("Amazon Jewellery");
    }

    public static List<String> getStores(){
        return stores;
    }
}
