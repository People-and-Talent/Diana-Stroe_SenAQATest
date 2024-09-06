package utils.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigurationReader;
import utils.WebDriverManager;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;


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

    @Given("The user copy pastes and insert in both fields")
    public void copyPaste() throws InterruptedException, IOException, UnsupportedFlavorException {
        log.info("Navigating to the login page");
        String URL = ConfigurationReader.getProperty("URL");
        WebDriverManager.getDriver().get(URL);
        PAGES.loginPage().checkCopyPaste();
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
