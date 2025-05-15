package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
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

        productPage.clickAddToCartAtProductPage();
        productPage.clickShopIcon();
    }

    @Test(groups = "Product")
    public void testCartProductName() {
        Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Backpack", "Product name mismatch");
    }
    @Test(groups = "Product")
    public void testCartProductQuantity() {
        Assert.assertEquals(cartPage.getProductQuantity(), "1", "Quantity mismatch");
    }
    @Test(groups = "Product")
    public void testCartProductDescription() {
        Assert.assertTrue(cartPage.getProductDescription().contains("carry.allTheThings()"), "Description mismatch");
    }
    @Test(groups = "Product")
    public void testCartProductPrice() {
        Assert.assertEquals(cartPage.getProductPrice(), "$29.99", "Price or currency mismatch");
    }
    @Test(groups = "Product")
    public void testRemoveProduct() {
        cartPage.clickRemoveButton();
        Assert.assertTrue(cartPage.isProductRemovedFromCart(), "Product was not removed from cart.");
    }

    // Buttons
    @Test(groups = "Buttons")
    public void testContinueShopping() {
        cartPage.clickContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html", "Continue shopping navigation failed.");
    }
    @Test(groups = "Buttons")
    public void testCheckoutNavigation() {
        cartPage.clickCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-one.html", "Checkout navigation failed.");
    }

    // Footer
    @Test(groups = "Footer Icons")
    public void testTwitterIconNavigation() {
        cartPage.clickTwitterIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://x.com/"), "Did not navigate to the LinkedIn page.");
    }
    @Test(groups = "Footer Icons")
    public void testFacebookIconNavigation() {
        cartPage.clickFacebookIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/"), "Did not navigate to the LinkedIn page.");
    }
    @Test(groups = "Footer Icons")
    public void testLinkedInIconNavigation() {
        cartPage.clickLinkedInIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/"), "Did not navigate to the LinkedIn page.");
    }


    // Menu
    @Test(groups = "Menu")
    public void testMenuButton() {
        cartPage.clickOnMenuButton();
        Assert.assertTrue(cartPage.isMenuSideBarDisplayed(), "Menu button is not displayed");
    }
    @Test(groups = "Menu")
    public void testAllItemsLink() {
        cartPage.clickOnMenuButton();
        cartPage.clickOnAllItemsLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }
    @Test(groups = "Menu")
    public void testAboutLink() {
        cartPage.clickOnMenuButton();
        cartPage.clickOnAboutLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"));

    }
    @Test(groups = "Menu")
    public void testLogout() {
        cartPage.clickOnMenuButton();
        cartPage.clickOnLogoutLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"));
    }
    @Test(groups = "Menu")
    public void testResetAppState() {
        cartPage.clickOnMenuButton();
        cartPage.clickOnResetAppState();
        cartPage.closeMenu();
        Assert.assertTrue(cartPage.isProductRemovedFromCart(),"Product is not removed from cart");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
