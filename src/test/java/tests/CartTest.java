package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class CartTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;


    // Footer

    @BeforeMethod
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        // Step 1: Login
        driver.get("https://www.saucedemo.com/v1");
        loginPage.loginAs("standard_user", "secret_sauce");

        productPage.clickAddToCart();
        productPage.clickShopIcon();
    }

    @Test
    public void testCartProductName() {
        Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack", "Product name mismatch");
    }

    @Test
    public void testCartProductQuantity() {
        Assert.assertEquals(cartPage.getProductQuantity(), "1", "Quantity mismatch");
    }

    @Test
    public void testCartProductDescription() {
        Assert.assertTrue(cartPage.getProductDescription().contains("carry.allTheThings()"), "Description mismatch");
    }

    @Test
    public void testCartProductPrice() {
        Assert.assertEquals(cartPage.getProductPrice(), "$29.99", "Price mismatch");
    }

    @Test
    public void testRemoveProduct() {
        cartPage.clickRemoveButton();
        Assert.assertTrue(cartPage.getProductName().isEmpty(), "Product was not removed from cart.");
    }

    @Test
    public void testContinueShopping() {
        cartPage.clickContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html", "Continue shopping navigation failed.");
    }

    @Test
    public void testCheckoutNavigation() {
        cartPage.clickCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-one.html", "Checkout navigation failed.");
    }

    @Test
    public void testFacebookIconNavigation() {
        cartPage.clickFacebookIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/"), "Did not navigate to the LinkedIn page.");
    }

    @Test
    public void testLinkedInIconNavigation() {
        cartPage.clickLinkedInIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/"), "Did not navigate to the LinkedIn page.");
    }

    @Test
    public void testShoppingCartIconNavigation() {
        cartPage.clickTwitterIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/cart.html"), "Did not navigate to shopping cart.");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
