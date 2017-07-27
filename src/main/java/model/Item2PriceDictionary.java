package model;

import java.util.HashMap;
import java.util.Map;


public class Item2PriceDictionary {
    private static Item2PriceDictionary ourInstance = new Item2PriceDictionary();
    private Map<String, Integer> item2Quantity2Price = new HashMap<>();

    private Item2PriceDictionary() {
    }

    public static Item2PriceDictionary getInstance() {
        return ourInstance;
    }

    public Map<String, Integer> getItem2Quantity2Price() {
        return item2Quantity2Price;
    }


}
