package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProductPage;
import java.util.List;

public class ProductTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        driver.get("https://www.saucedemo.com/v1/");
        loginPage.loginAs("standard_user", "secret_sauce");

    }

    // Menu
    @Test(groups = "Menu")
    public void testMenuButton() {
        productPage.clickOnMenuButton();
        Assert.assertTrue(productPage.isMenuSideBarDisplayed(), "Menu button is not displayed");
    }
    @Test(groups = "Menu")
    public void testAllItemsLink() {
        productPage.clickOnMenuButton();
        productPage.clickOnAllItemsLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }
    @Test(groups = "Menu")
    public void testAboutLink() {
        productPage.clickOnMenuButton();
        productPage.clickOnAboutLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"));

    }
    @Test(groups = "Menu")
    public void testLogout() {
        productPage.clickOnMenuButton();
        productPage.clickOnLogoutLink();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"));
    }
    @Test(groups = "Menu")
    public void testResetAppState() {
        productPage.clickAddToCartAtProductPage();
        productPage.clickOnMenuButton();
        productPage.clickOnResetAppState();
        productPage.closeMenu();
        productPage.clickShopIcon();
        Assert.assertTrue(productPage.isProductRemovedFromCart(),"Product is not removed from cart");

    }

    // Cart icon
    @Test(groups = "Cart")
    public void testShoppingCartIconNavigation() {
        productPage.clickShopIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/cart.html"), "Did not navigate to shopping cart.");
    }


    // Product
    @Test(groups = "Product")
    public void testAddProductToCartFromProductPage() {
        productPage.clickAddToCartAtProductPage();
        productPage.clickShopIcon();
        Assert.assertTrue(productPage.isProductAddedToCartPage(), "Product was not added to the cart.");
    }
    @Test(groups = "Product")
    public void testRemoveProductFromCartFromProductPage() {
        productPage.clickAddToCartAtProductPage();
        productPage.clickRemoveButton();
        productPage.clickShopIcon();
        Assert.assertTrue(productPage.isProductRemovedFromCart(), "Product was not removed from the cart.");
    }
    @Test(groups = "Product")
    public void testProductImageNavigation() {
        productPage.clickProductImage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory-item.html?id=4"), "Did not navigate to product details page.");
    }
    @Test(groups = "Product")
    public void testProductNameNavigation() {
        productPage.clickProductName();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory-item.html?id=4"), "Did not navigate to product details page.");
    }
    @Test(groups = "Product")
    public void testProductPrice() {
        Assert.assertTrue(productPage.isProductPriceDisplayed(), "Product price is not displayed.");
    }
    @Test(groups = "Product")
    public void testProductDescription() {
        Assert.assertTrue(productPage.isProductDescriptionDisplayed(), "Product description is not displayed.");
    }
    @Test(groups = "Product")
    public void testAddProductToCartFromProductDetailsPage() {
        productPage.clickProductImage();
        productPage.clickAddToCartAtProductDetailsPage();
        productPage.clickShopIcon();
        Assert.assertTrue(productPage.isProductAddedToCartPage(), "Product was not added to the cart.");
    }
    @Test(groups = "Product")
    public void testRemoveProductFromCartFromProductDetailsPage() {
        productPage.clickProductImage();
        productPage.clickAddToCartAtProductDetailsPage();
        productPage.clickRemoveButtonAtProductDetailsPage();
        productPage.clickShopIcon();
        Assert.assertTrue(productPage.isProductRemovedFromCart(), "Product was not removed from the cart.");
    }

    // Filteration
    @Test(groups = "Filteration")
    public void testProductFilterByNameA_To_Z() {
        productPage.selectFilterOption("Name (A to Z)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Backpack", "Incorrect sorting by Name (A to Z)");
    }
    @Test(groups = "Filteration")
    public void testProductFilterByNameZ_To_A() {
        productPage.selectFilterOption("Name (Z to A)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "Incorrect sorting by Name (Z to A)");
    }
    @Test(groups = "Filteration")
    public void testProductFilterByPriceLow_To_High() {
        productPage.selectFilterOption("Price (low to high)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Onesie", "Incorrect sorting by Price (Low to High)");
    }
    @Test(groups = "Filteration")
    public void testProductFilterByPriceHigh_To_Low() {
        productPage.selectFilterOption("Price (high to low)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Fleece Jacket", "Incorrect sorting by Name (High to Low)");
    }


    // Footer
    @Test(groups = "Footer Icons")
    public void testTwitterIconNavigation() {
        productPage.clickTwitterIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://x.com/"), "Did not navigate to the LinkedIn page.");

    }
    @Test(groups = "Footer Icons")
    public void testFacebookIconNavigation() {
        productPage.clickFacebookIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/"), "Did not navigate to the LinkedIn page.");
    }
    @Test(groups = "Footer Icons")
    public void testLinkedInIconNavigation() {
        productPage.clickLinkedInIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/"), "Did not navigate to the LinkedIn page.");
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser after test
        driver.quit();
    }
}
