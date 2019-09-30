package org.turingsquad.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.turingsquad.sender.AtsdSender;
import org.turingsquad.sender.Sender;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Slf4j
public class Config {
    private final String protocol;
    private final String serverName;
    private final int port;
    private final String username;
    private final String password;
    private final ServerType serverType;
    private final String url;

    public static final Config INSTANCE = init();
    private static final String DEFAULT_RESOURCE_FILE = "client.properties";

    private Config(String filePath) {
        Properties properties = new Properties();
        try (InputStream stream = new FileInputStream(filePath)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Failed to load client properties. {}", e.getMessage());
            throw new RuntimeException("Could not load config from " + filePath);
        }
        protocol = load("protocol", properties, "http");
        serverName = load("serverName", properties, "localhost");
        port = Integer.parseInt(load("port", properties, "8088"));
        username = load("username", properties, "axibase");
        password = load("password", properties, "password");
        serverType = ServerType.valueOf(load("serverType", properties, "ATSD"));
        url = String.format("%s://%s:%d", protocol, serverName, port);
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

    public Sender getSender() {
        switch (serverType) {
            case ATSD:
                return new AtsdSender(url + "/api/v1", username, password);
            default:
                throw  new RuntimeException("Cannot get sender for " + serverType);
        }
    }

    private static Config init() {
        String filePath = System.getProperty("configFile");
        if(filePath == null) {
            return new Config(Config.class.getClassLoader().getResource(DEFAULT_RESOURCE_FILE).getFile());
        }
        return new Config(filePath);
    }

    public enum ServerType {
        ATSD
    }
}
