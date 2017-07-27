package model;

import java.util.HashMap;
import java.util.Map;


public class Word2RomanDictionary {
    private static Word2RomanDictionary ourInstance = new Word2RomanDictionary();
    private Map<String, String> word2Roman = new HashMap<>();

    private Word2RomanDictionary() {
    }

    public static Word2RomanDictionary getInstance() {
        return ourInstance;
    }

    public Map<String, String> getWord2Roman() {
        return word2Roman;
    }
}
