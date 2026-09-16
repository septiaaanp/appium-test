package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * CartPage - handles cart / delivery selection screen.
 * Exposes: choose delivery method, Beli button.
 */
public class CartPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/rb_delivery")
    private WebElement deliveryOption;


    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_shipping_fee")
    private WebElement shippingFeeLabel;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_insurance_fee")
    private WebElement insuranceFeeLabel;

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public void chooseDelivery() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryOption));
        deliveryOption.click();
    }

    /**
     * Clicks a button on this screen by its visible label text.
     * Uses BasePage.clickButtonByLabel — equivalent of web button[text()='label'].
     */
    public void clickButton(String label) {
        clickButtonByLabel(label);
    }

    public String getShippingFee() {
        wait.until(ExpectedConditions.visibilityOf(shippingFeeLabel));
        return shippingFeeLabel.getText();
    }

    public String getInsuranceFee() {
        wait.until(ExpectedConditions.visibilityOf(insuranceFeeLabel));
        return insuranceFeeLabel.getText();
    }
}
