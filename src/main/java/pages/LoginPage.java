package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * LoginPage - handles the login screen of Klik Indomaret.
 */
public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/et_email")
    private WebElement emailField;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/et_password")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/btn_login")
    private WebElement loginButton;

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}
