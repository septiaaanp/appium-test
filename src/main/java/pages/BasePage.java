package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - parent for all Page Object classes.
 * Initialises PageFactory with AppiumFieldDecorator so subclasses can use @AndroidFindBy.
 *
 * Generic helpers:
 *   - clickButtonByLabel(label) : equivalent of web //button[text()='label']
 *   - selectOptionByLabel(label): for list/radio options identified by visible text
 */
public abstract class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @SuppressWarnings("ThisEscapedInObjectConstruction")
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Clicks any clickable element whose visible text matches the given label.
     * Equivalent of web: //button[text()='label'] | //*[@text='label']
     */
    public void clickButtonByLabel(String label) {
        By locator = By.xpath(
                "//*[@text='" + label + "' and (" +
                "@class='android.widget.Button' or " +
                "@class='android.widget.TextView' or " +
                "@class='android.widget.ImageButton')]"
        );
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Selects a list/radio option by its visible label text.
     * Equivalent of web: //input[@type='radio']/following-sibling::label[text()='label']
     */
    public void selectOptionByLabel(String label) {
        By locator = By.xpath("//*[@text='" + label + "']");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}
