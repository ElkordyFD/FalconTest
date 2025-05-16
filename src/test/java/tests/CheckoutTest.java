package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class CheckoutTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Login and go to checkout page
        driver.get("https://www.saucedemo.com/v1/"); // Adjust if different
        loginPage.loginAs("standard_user", "secret_sauce"); // Replace with your credentials

        productPage.clickAddToCartAtProductPage();
        productPage.clickShopIcon();

        cartPage.clickCheckout();
    }

    @Test
    public void testCancelCheckout() {
        checkoutPage.clickCancel();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/cart.html", "Cancel did not return to the cart page.");
    }

    @Test(groups = "Happy Scenario")
    public void testCheckoutwithValidCredentials() {
        checkoutPage.fillCheckoutForm("Mina","George","11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithEmptyFirstName() {
        checkoutPage.fillCheckoutForm("", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithSpecialCharactersAtFirstName(){
        checkoutPage.fillCheckoutForm("#@!$", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithNumbersAtFirstName() {
        checkoutPage.fillCheckoutForm("1234", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithArabicAtFirstName() {
        checkoutPage.fillCheckoutForm("مينا", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithSpacesOnlyAtFirstName() {
        checkoutPage.fillCheckoutForm("    ", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithLeadingSpacesAtFirstName(){
        checkoutPage.fillCheckoutForm("    Mina", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithTrailingSpacesAtFirstName() {
        checkoutPage.fillCheckoutForm("Mina      ", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid FirstName")
    public void testCheckoutWithSpacesBetweenAtFirstName(){
        checkoutPage.fillCheckoutForm("M i n a", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithEmptyLastName() {
        checkoutPage.fillCheckoutForm("Mina", "", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithSpecialCharactersAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "@#$%", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithNumbersAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "1234", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithArabicAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "جورج", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithSpacesOnlyAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "    ", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithLeadingSpacesAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "   George", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithTrailingSpacesAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George      ", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid LastName")
    public void testCheckoutWithSpacesBetweenAtLastName(){
        checkoutPage.fillCheckoutForm("Mina", "Ge or ge", "11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithEmptyZipCode() {
        checkoutPage.fillCheckoutForm("Mina","George","");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");

    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithLowercaseCharactersInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "abcd");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithUppercaseCharactersInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "ABCD");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithSpecialCharactersInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "@#$%^&*");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithArabicInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "بوستال كود");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithNegativeNumberInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "-375345");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutwithFractionNumberInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "37.5450");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithLeadingSpacesInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "  11311");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithTrailingSpacesInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "11311  ");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithSpacesBetweenInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "3 75 50");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithSpacesOnlyInZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "    ");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithFourDigitsZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "3753");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    @Test (groups = "Invalid ZipCode")
    public void testCheckoutWithSixDigitsZipCode(){
        checkoutPage.fillCheckoutForm("Mina", "George", "375345");
        checkoutPage.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html", "Continue did not proceed to nextstep.");
    }

    // Footer
    @Test
    public void testTwitterIconNavigation() {
        checkoutPage.clickTwitterIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://x.com/"), "Did not navigate to the LinkedIn page.");

    }
    @Test
    public void testFacebookIconNavigation() {
        checkoutPage.clickFacebookIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/"), "Did not navigate to the LinkedIn page.");
    }
    @Test
    public void testLinkedInIconNavigation() {
        checkoutPage.clickLinkedInIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/"), "Did not navigate to the LinkedIn page.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
