package pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();
    public static final String PATH_PROPERTIES = "application.properties";

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String getProperty(String key) {
        String property = PROPERTIES.getProperty(key);
        return property;
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class
                .getClassLoader()
                .getResourceAsStream(PATH_PROPERTIES)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);//TODO: CREATE CUSTOM EXCEPTION
        }
    }
}