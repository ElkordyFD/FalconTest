package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class CheckoutTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Login and go to checkout page
        driver.get("https://www.saucedemo.com/v1/"); // Adjust if different
        loginPage.loginAs("standard_user", "secret_sauce"); // Replace with your credentials

        productPage.clickAddToCartAtProductPage();
        productPage.clickShopIcon();

        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
    }

    @Test
    public void testCancelCheckout() {
        checkoutPage.fillCheckoutForm("John", "Doe", "12345");
        checkoutPage.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/cart.html"), "Cancel did not return to the cart page.");
    }

    @Test
    public void testContinueCheckout() {
        checkoutPage.fillCheckoutForm("Jane", "Smith", "54321");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to next step.");
    }

    @Test
    public void testCheckoutwithValidCredentials() {
        checkoutPage.fillCheckoutForm("Mina","George","11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithavalidFirstNameandLastNameleaveZipPostalcodeFieldempty() {
        checkoutPage.fillCheckoutForm("Mina","George","");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");

    }

    @Test
    public void testCheckoutwithValidnameandzipfieldleavethelastnamefieldempty() {
        checkoutPage.fillCheckoutForm("Mina", "", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithavalidFirstNameandZipPostalcodeleaveLastNameFieldempty(){
        checkoutPage.fillCheckoutForm("", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithaspecialcharactersinFirstNameFieldandEntervalidLastNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("#@!$", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithNumbersinFirstNameFieldandEntervalidLastNameandZipPostalcode() {
        checkoutPage.fillCheckoutForm("1234", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithArabiccharactersinFirstNameFieldandEntervalidLastNameandZipPostalcode() {
        checkoutPage.fillCheckoutForm("مينا", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwith4spacesinFirstNameFieldandEntervalidLastNameandZipPostalcode() {
        checkoutPage.fillCheckoutForm("    ", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidfirstnamewithspacesbeforeEntervalidlastNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("    Mina", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidfirstnamewithspacesafterEntervalidlastNameandZipPostalcode() {
        checkoutPage.fillCheckoutForm("Mina      ", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidfirstnamewithspacesbetweenEntervalidlastNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("M i n a", "George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithspecialcharactersinLastNameFieldandEntervalidFirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "@#$%", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithNumbersinLastNameFieldandEntervalidFirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "1234", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithArabiccharactersinLastNameFieldandEntervalidFirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "جورج", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwith4spacesinLastNameFieldandEntervalidFirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "    ", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidlastnamewithspacesbeforeEntervalidfirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "   George", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidlastnamewithspacesafterEntervalidfirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "George      ", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithinvalidlastnamewithspacesbetweenEntervalidfirstNameandZipPostalcode(){
        checkoutPage.fillCheckoutForm("Mina", "Ge or ge", "11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithcharactersinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "abcd");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithUppercaselettersinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "ABCD");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithspecialcharactersinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "@#$%^&*");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithArabiccharactersinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "بوستال كود");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithanegativenumberinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "-375345");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithadecimalnumberinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "37.5450");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithavalidnumberwithaleadingspaceinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "  11311");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithavalidnumberwithaTrailingspaceinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "11311  ");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithavalidnumberwithspacesbetweeninZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "3 75 50");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithFourSpacesinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "    ");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithNumbersInZipPostalcodeFieldAndEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "3753");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

    @Test
    public void testCheckoutwithSixnumbersinZipPostalcodeFieldandEntervalidFirstNameandLastName(){
        checkoutPage.fillCheckoutForm("Mina", "George", "375345");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-two.html"), "Continue did not proceed to nextstep.");
    }

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
