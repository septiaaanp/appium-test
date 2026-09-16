package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * PaymentPage - handles the Pembayaran screen.
 * Exposes: choose Virtual Account, Bayar sekarang button.
 */
public class PaymentPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_total_price")
    private WebElement totalPriceLabel;

    public PaymentPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Selects a payment option by its label text.
     * Uses BasePage.selectOptionByLabel — equivalent of web option[text()='label'].
     */
    public void selectPaymentOption(String label) {
        selectOptionByLabel(label);
    }

    /**
     * Clicks a button on this screen by its visible label text.
     * Uses BasePage.clickButtonByLabel — equivalent of web button[text()='label'].
     */
    public void clickButton(String label) {
        clickButtonByLabel(label);
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPriceLabel));
        return totalPriceLabel.getText();
    }
}
