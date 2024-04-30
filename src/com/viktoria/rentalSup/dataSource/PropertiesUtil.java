package com.viktoria.rentalSup.dataSource;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String APPLICATION_PROPERTIES = "application.properties";

    static {
        loadProperties();
    }


    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            PROPERTIES.load(inputStream);
        }
    }
}

