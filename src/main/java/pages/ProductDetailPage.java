package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ProductDetailPage - handles product detail screen.
 * Exposes: product name, product price, Add to Cart button.
 */
public class ProductDetailPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_product_name")
    private WebElement productName;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/tv_product_price")
    private WebElement productPrice;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/btn_add_to_cart")
    private WebElement addToCartButton;

    public ProductDetailPage(AndroidDriver driver) {
        super(driver);
    }

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.getText();
    }

    /**
     * Verifies the product price matches the expected value.
     * Strips currency formatting before comparing so "Rp10.000" == "10.000" == "10000".
     */
    public boolean isPriceEqualTo(String expectedPrice) {
        String actual = getProductPrice();
        String actualClean = actual.replaceAll("[^0-9]", "");
        String expectedClean = expectedPrice.replaceAll("[^0-9]", "");
        return actualClean.equals(expectedClean);
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }
}
