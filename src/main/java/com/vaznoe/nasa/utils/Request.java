package com.vaznoe.nasa.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vaznoe
 * Date: 2/16/19
 */
public class Request {

    private Map<String, Object> map = new HashMap<>();

    public Request add(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Map<String, Object> get() {
        return this.map;
    }
}
