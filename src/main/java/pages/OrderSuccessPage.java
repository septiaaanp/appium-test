package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * OrderSuccessPage - confirms the user has reached the order success screen.
 */
public class OrderSuccessPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_order_success")
    private WebElement orderSuccessMessage;

    public OrderSuccessPage(AndroidDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        return orderSuccessMessage.getText();
    }

    public boolean isOrderSuccessDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));
            return orderSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
