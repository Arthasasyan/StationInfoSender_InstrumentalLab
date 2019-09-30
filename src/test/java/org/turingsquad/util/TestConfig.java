package org.turingsquad.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Slf4j
public class TestConfig {
    private final String atsdUrl;
    private final String atsdUserName;
    private final String atsdPassword;

    public static final TestConfig INSTANCE = init();

    private TestConfig(String filePath) {
        Properties properties = new Properties();
        try (InputStream stream = new FileInputStream(filePath)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Failed to load mock properties. {}", e.getMessage());
            throw new RuntimeException("Could not load config from " + filePath);
        }
        atsdUrl = load("atsd.url", properties, "http://localhost:8088");
        atsdUserName = load("atsd.username", properties, "axibase");
        atsdPassword = load("atsd.password", properties, "password");
    }

    private static String load(String name, Properties clientProperties, String defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            value = clientProperties.getProperty(name);
            if (value == null) {
                if (defaultValue == null) {
                    log.error("Could not find required property: {}", name);
                    throw new IllegalStateException(name + " property is null");
                } else {
                    value = defaultValue;
                }
            }
        }
        return value;
    }


    private static final String DEFAULT_RESOURCE_FILE = "mock.properties";

    private static final TestConfig init() {
        String filePath = System.getProperty("mockConfigFile");
        if(filePath == null) {
            return new TestConfig(Config.class.getClassLoader().getResource(DEFAULT_RESOURCE_FILE).getFile());
        }
        return new TestConfig(filePath);
    }
}
