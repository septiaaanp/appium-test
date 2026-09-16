package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * SearchPage - handles product search on the home/search screen.
 */
public class SearchPage extends BasePage {

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/et_search")
    private WebElement searchField;

    @AndroidFindBy(id = "com.indomaret.klikindomaret:id/btn_search")
    private WebElement searchButton;

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.clear();
        searchField.sendKeys(productName);
        searchButton.click();
    }
}
