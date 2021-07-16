package com.stoom.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

/**
 * Utility class to help with unit tests.
 */
public class TestUtilities {

    /**
     * Map a Json to a Object of specified class.
     *
     * @param name         Name of json file.
     * @param clazz        Class of object destination.
     * @param isCollection Define if Object is a collection or a single element.
     * @return The object mapped.
     */
    @SneakyThrows
    public static Object mapJsonToClass(String name, Class<?> clazz, boolean isCollection) {
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
