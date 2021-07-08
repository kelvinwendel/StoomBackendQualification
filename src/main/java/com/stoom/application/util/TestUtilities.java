package com.stoom.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestUtilities {
    public static Object mapJsonToClass(String name, Class<?> clazz, boolean isCollection) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(".\\src\\main\\resources\\Mocks\\" + name + ".json");

        if (isCollection) {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            List<?> result = mapper.readValue(file, type);

            return result;
        } else {
            Object result = mapper.readValue(file, clazz);

            return result;
        }
    }
}
