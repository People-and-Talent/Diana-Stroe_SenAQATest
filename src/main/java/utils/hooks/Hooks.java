package utils.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigurationReader;
import utils.WebDriverManager;


public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        log.info(":::::::::::::::: TEST INFORMATION ::::::::::::::::");
        log.info("Test Environment :: " + ConfigurationReader.TEST_ENVIRONMENT);
        log.info("Browser type :: " + ConfigurationReader.getProperty("BROWSER"));
        log.info("URL :: " + ConfigurationReader.getProperty("URL"));
        log.info("Test scenario :: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) WebDriverManager.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", scenario.getName());
        }
        WebDriverManager.closeDriver();
    }
}
