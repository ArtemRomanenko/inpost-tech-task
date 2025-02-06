package utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyReader {
    private final Properties properties = new Properties();
    private static final String DEFAULT_ENV = "test";
    private String env;
    private String configFile;

    public PropertyReader() {
        this.env = System.getProperty("env", DEFAULT_ENV);
        this.configFile = "config/env-" + env + ".properties";
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new IOException("Could not find properties file: " + configFile);
            }
            properties.load(input);
        } catch (IOException e) {
            log.info("Failed to load properties - {}", e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
