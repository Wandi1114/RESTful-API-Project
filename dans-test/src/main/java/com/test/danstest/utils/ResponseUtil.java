package com.test.danstest.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    ResponseUtil(String message, Object payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("payload", payload);
    }
}
