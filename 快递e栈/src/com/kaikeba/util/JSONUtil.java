package com.kaikeba.util;

import com.google.gson.Gson;

public class JSONUtil {
    private static Gson gson = new Gson();
    public static String toJSON(Object obj) {
        return gson.toJson(obj);
    }
}
