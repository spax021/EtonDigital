package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    private static String fileLocation = ".\\src\\test\\resources\\application.properties";

    private PropertiesFile() {
    }

    private static class PropertiesFileHolder {
        private static final Properties INSTANCE = loadProperties();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileLocation)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getInstance() {
        return PropertiesFileHolder.INSTANCE;
    }

    public static String getBasicAuth() {
        return getInstance().getProperty("basicAuth");
    }

    public static String getBaseUrl() {
        return getInstance().getProperty("baseUrl");
    }

    public static String getEmail() {
        return getInstance().getProperty("email");
    }

    public static String getPassword() {
        return getInstance().getProperty("password");
    }

    public static String getInvalidUEmail() {
        return getInstance().getProperty("invalidEmail");
    }

    public static String getInvalidPassword() {
        return getInstance().getProperty("invalidPassword");
    }
}
