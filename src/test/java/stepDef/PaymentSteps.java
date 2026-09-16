package stepDef;

import driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import pages.OrderSuccessPage;
import pages.CartPage;
import pages.PaymentPage;
import pages.ProductDetailPage;
import pages.SearchPage;
import utils.PriceCalculator;

/**
 * PaymentSteps - step definitions for the Virtual Account payment flow.
 *
 * Price data collected during the flow is stored as instance variables
 * so the final assertion can compare calculated total vs actual total.
 */
public class PaymentSteps {

    private final LoginPage loginPage;
    private final SearchPage searchPage;
    private final ProductDetailPage productDetailPage;
    private final CartPage cartPage;
    private final PaymentPage paymentPage;
    private final OrderSuccessPage orderSuccessPage;

    // Captured during test flow for final calculation assertion
    private String productPrice;
    private String shippingFee;
    private String insuranceFee;
    private String displayedTotal;

    // Captured expected product price for end-of-scenario price integrity assertion
    private String expectedProductPrice;

    public PaymentSteps() {
        loginPage = new LoginPage(DriverManager.getDriver());
        searchPage = new SearchPage(DriverManager.getDriver());
        productDetailPage = new ProductDetailPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        paymentPage = new PaymentPage(DriverManager.getDriver());
        orderSuccessPage = new OrderSuccessPage(DriverManager.getDriver());
    }

    @Given("User is logged in to Klik Indomaret with email {string} and password {string}")
    public void userIsLoggedIn(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @When("User searches for product {string}")
    public void userSearchesForProduct(String productName) {
        searchPage.searchProduct(productName);
    }

    @And("User opens the first product detail page")
    public void userOpensProductDetailPage() {
        productPrice = productDetailPage.getProductPrice();
    }

    @And("User sees the product price is {string}")
    public void userSeesProductPrice(String expectedPrice) {
        expectedProductPrice = expectedPrice;
        Assert.assertTrue(
                "Product price does not match expected: " + expectedPrice,
                productDetailPage.isPriceEqualTo(expectedPrice)
        );
    }

    @And("User adds the product to cart")
    public void userAddsProductToCart() {
        productDetailPage.clickAddToCart();
    }

    @And("User chooses Delivery method")
    public void userChoosesDelivery() {
        cartPage.chooseDelivery();
        shippingFee = cartPage.getShippingFee();
        insuranceFee = cartPage.getInsuranceFee();
    }

    /**
     * Generic button click by visible label text.
     * Equivalent of web: //button[text()='label']
     * Android XPath: //*[@text='label' and @class='android.widget.Button']
     */
    @And("User clicks button {string}")
    public void userClicksButton(String label) {
        // Delegate to the current page's BasePage.clickButtonByLabel
        // CartPage and PaymentPage both expose clickButton(label) which calls BasePage
        cartPage.clickButtonByLabel(label);
    }

    @And("User selects payment option {string}")
    public void userSelectsPaymentOption(String optionLabel) {
        paymentPage.selectPaymentOption(optionLabel);
        displayedTotal = paymentPage.getTotalPrice();
    }

    @Then("User should be redirected to order success page")
    public void userShouldBeOnOrderSuccessPage() {
        Assert.assertTrue("Order success page not displayed", orderSuccessPage.isOrderSuccessDisplayed());
    }

    @Then("The total price displayed should match the calculated total")
    public void theTotalPriceShouldMatchCalculation() {
        long calculated = PriceCalculator.calculateTotal(productPrice, shippingFee, insuranceFee);
        long displayed = PriceCalculator.parsePrice(displayedTotal);
        Assert.assertEquals(
                String.format("Total mismatch — displayed: %d, calculated: %d", displayed, calculated),
                calculated,
                displayed
        );
    }

    @And("I see the product price is still {string}")
    public void iSeeProductPriceIsStill(String expectedPrice) {
        String priceToCheck = (expectedPrice != null && !expectedPrice.isEmpty())
                ? expectedPrice
                : expectedProductPrice;
        Assert.assertTrue(
                "Product price changed! Expected: " + priceToCheck,
                productDetailPage.isPriceEqualTo(priceToCheck)
        );
    }
}
