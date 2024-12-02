package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();
    static {
        loadProperties();
    }
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties(){
        try(var applicationProperties =  PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");) {
            PROPERTIES.load(applicationProperties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
