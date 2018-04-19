package com.kittycoder.datadisplay.po;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by shucheng on 2018/4/17.
 */
public class PageResult {

    private HashMap<String, String> stringHashMap;
    private LinkedHashMap<String, String> stringLinkedHashMap;

    public HashMap<String, String> getStringHashMap() {
        return stringHashMap;
    }

    public void setStringHashMap(HashMap<String, String> stringHashMap) {
        this.stringHashMap = stringHashMap;
    }

    public LinkedHashMap<String, String> getStringLinkedHashMap() {
        return stringLinkedHashMap;
    }

    public void setStringLinkedHashMap(LinkedHashMap<String, String> stringLinkedHashMap) {
        this.stringLinkedHashMap = stringLinkedHashMap;
    }
}
