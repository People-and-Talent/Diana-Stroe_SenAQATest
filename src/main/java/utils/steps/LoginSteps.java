package utils.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import utils.ConfigurationReader;
import utils.Pages;
import utils.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends BaseStep {
    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    WebDriverWait wait=new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(5));

    @Given("The user will login with username {string} and password {string}")
    public void loginSuccessfully(String username, String password) throws InterruptedException {
        log.info("Navigating to the login page");
        String URL = ConfigurationReader.getProperty("URL");
        WebDriverManager.getDriver().get(URL);
        PAGES.loginPage().login(username,password);
    }


    @Then("Check the login was successful")
    public void checkSuccess(){
        PAGES.loginPage().checkSuccessfulLogin();
    }

    @Then("Check the login wasn't successful and a warning is displayed")
    public void checkMissingUsernameLoginAttempt() throws InterruptedException {
        PAGES.loginPage().checkLoginErrorMessage();
    }

}
