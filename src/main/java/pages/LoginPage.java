package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {
    //public WebDriver driver;
    private static final String url="https://github.com/login";
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));


    //Locators
    @FindBy(name="login")
    WebElement emailTextBox;


    @FindBy(name="password")
  WebElement passTextBox;

    @FindBy(name="commit")
    WebElement submitBtn;

    @FindBy(xpath="//*[@id=\"js-flash-container\"]/div/div/div")
    WebElement errorAlert;

    @FindBy(xpath="//*[@id=\"dashboard\"]/div/feed-container/div[1]/h2")
    WebElement home;


    //Methods
    public void login(String username, String password) throws InterruptedException {
        emailTextBox.sendKeys(username);
        Thread.sleep(3000);
        passTextBox.sendKeys(password);
        Thread.sleep(3000);
        submitBtn.click();
        Thread.sleep(3000);

    }

    public void checkSuccessfulLogin(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"dashboard\"]/div/feed-container/div[1]/h2")));
    }

    public void checkLoginErrorMessage(){
        assertThat(errorAlert.isDisplayed());
    }


}
