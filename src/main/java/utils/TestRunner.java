package utils;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/features",
        plugin = { "pretty","html:target/cucumber-pretty",
                "json:target/cucumber.json" },
        glue = "TestRunner/steps",
        dryRun = true)
public class TestRunner {
}
