package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);

    private static final Properties CONFIG_FILE;

    public static final String TEST_ENVIRONMENT = System.getProperty("testEnv", "devInt");

    //IN case we have multiple environment to test on
    static {
        String environmentConfiguration = null;
        switch (TEST_ENVIRONMENT) {
            case "devInt" -> environmentConfiguration = "devIntConfiguration";
            case "devExt" -> environmentConfiguration = "devExtConfiguration";
            case "stage" -> environmentConfiguration = "stageConfiguration";
            default -> {
                log.error("Environment type not supported or environment is not set: {}", TEST_ENVIRONMENT);
                throw new IllegalArgumentException(
                        "Environment type not supported or environment is not set: " + TEST_ENVIRONMENT);
            }
        }
        try (FileInputStream input = new FileInputStream(
                "src/main/resources/configurations/" + environmentConfiguration + ".properties")) {
            CONFIG_FILE = new Properties();
            CONFIG_FILE.load(input);

        }
        catch (IOException e) {
            log.error("Failed to load properties file!", e);
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    /**
     * This method returns property value from configuration.properties file
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return CONFIG_FILE.getProperty(keyName);
    }

}