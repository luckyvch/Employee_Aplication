package com.employee.edu.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.stream.Collectors;


public class TypeConverter {

    /**
     * convert object to map. if object contains
     * nullable fields it doesn't save in the map.
     * This map is needed for filtering employees
     *
     * @param object
     * @return Map<String, String>
     */
    public static Map<String, String> ObjectToMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        //noinspection unchecked
        Map<String, String> map = objectMapper.convertValue(object, Map.class);
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, newEntry -> String.valueOf(newEntry.getValue())));
    }

}
