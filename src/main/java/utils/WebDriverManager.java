package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    private WebDriverManager() {
        throw new UnsupportedOperationException("Can not instantiate Utility (Diver) class");
    }

    /**
     * Returns a new instance of a WebDriver based on the browser that is specified in the
     * configuration.properties file. If an instance of a WebDriver already exist, that
     * instance will be returned.
     * <p>
     * Synchronized makes method thread safe. It ensures that only 1 thread can use it at
     * the time. Thread safety reduces performance but it makes everything safe.
     * @return WebDriver - new WebDriver instance
     * @throws RuntimeException if an unsupported or invalid browser is specified in the
     * configuration properties files
     */

    public static WebDriver getDriver() {
        if (DRIVER_POOL.get() == null) {
            synchronized (WebDriverManager.class) {
                if (DRIVER_POOL.get() == null) {
                    String browser = ConfigurationReader.getProperty("BROWSER").toLowerCase();
                    WebDriver driver;

                    switch (browser) {
                        case "firefox" -> driver = createFireFoxDriver();
                        case "edge" -> driver = createEdgeDriver();
                        case "chrome-headless" -> driver = createChromeHeadlessDriver();
                        default -> driver = createChromeDriver();
                    }
                    DRIVER_POOL.set(driver);
                }
            }
        }
        return DRIVER_POOL.get();
    }

    private static WebDriver createChromeHeadlessDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080", "--ignore-certificate-errors");
        chromeOptions.setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--ignore-certificate-errors");
        chromeOptions.setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createFireFoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized", "--ignore-certificate-errors");
        return new FirefoxDriver(firefoxOptions);
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized", "--ignore-certificate-errors");
        edgeOptions.setAcceptInsecureCerts(true);
        return new EdgeDriver(edgeOptions);
    }

    public static void closeDriver() {
        WebDriver driver = DRIVER_POOL.get();
        if (driver != null) {
            driver.quit();
            DRIVER_POOL.remove();
        }
    }

}