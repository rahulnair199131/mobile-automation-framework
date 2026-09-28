package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input =
                     new FileInputStream(
                             "src/test/resources/config/config.properties")) {

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getPlatform() {
        return System.getProperty(
                "platform",
                getProperty("platform")
        );
    }

    public static String getDeviceName() {
        return getProperty(
                getPlatform() + ".device.name"
        );
    }

    public static String getDeviceUdid() {
        return getProperty(
                getPlatform() + ".device.udid"
        );
    }

    public static String getAppPath() {
        return new java.io.File(
                getProperty(getPlatform() + ".app.path")
        ).getAbsolutePath();
    }

    public static int getExplicitWait() {
        return Integer.parseInt(
                getProperty("explicit.wait")
        );
    }

    public static String getCucumberTags() {
        return getProperty("cucumber.tags");
    }
}