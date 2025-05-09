package tests;

import org.openqa.selenium.By;
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
    private ProductPage productPage;
    private LoginPage loginPage;

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

    @Test
    public void testAddToCart() {
        productPage.clickAddToCart();
        Assert.assertTrue(productPage.isRemoveButtonDisplayed(), "Product was not added to the cart.");
    }

    @Test
    public void testRemoveFromCart() {
        productPage.clickRemoveButton();
        Assert.assertTrue(productPage.isAddButtonDisplayed(), "Product was not removed from the cart.");
    }

    @Test
    public void testProductImageNavigation() {
        productPage.clickProductImage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory-item.html?id=4"), "Did not navigate to product details page.");
    }

    @Test
    public void testProductNameNavigation() {
        productPage.clickProductName();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory-item.html?id=4"), "Did not navigate to product details page.");
    }

    @Test
    public void testProductPrice() {
        Assert.assertTrue(productPage.isProductPriceDisplayed(), "Product price is not displayed.");
    }

    @Test
    public void testProductDescription() {
        Assert.assertTrue(productPage.isProductDescriptionDisplayed(), "Product description is not displayed.");
    }

    @Test
    public void testTwitterIconNavigation() {
        productPage.clickTwitterIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://x.com/"), "Did not navigate to the LinkedIn page.");

    }

    @Test
    public void testFacebookIconNavigation() {
        productPage.clickFacebookIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/"), "Did not navigate to the LinkedIn page.");
    }

    @Test
    public void testLinkedInIconNavigation() {
        productPage.clickLinkedInIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/"), "Did not navigate to the LinkedIn page.");
    }

    @Test
    public void testShoppingCartIconNavigation() {
        productPage.clickShopIcon();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/cart.html"), "Did not navigate to shopping cart.");
    }


    @Test
    public void testProductFilterByNameA_To_Z() {
        productPage.selectFilterOption("Name (A to Z)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Backpack", "Incorrect sorting by Name (A to Z)");
    }
    @Test
    public void testProductFilterByNameZ_To_A() {
        productPage.selectFilterOption("Name (Z to A)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "Incorrect sorting by Name (Z to A)");
    }
    @Test
    public void testProductFilterByPriceLow_To_High() {
        productPage.selectFilterOption("Price (low to high)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Onesie", "Incorrect sorting by Price (Low to High)");
    }
    @Test
    public void testProductFilterByPriceHigh_To_Low() {
        productPage.selectFilterOption("Price (high to low)");
        List<String> names = productPage.getProductNames();
        // Simple check for the first product
        Assert.assertEquals(names.get(0), "Sauce Labs Fleece Jacket", "Incorrect sorting by Name (High to Low)");
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser after test
        driver.quit();
    }
}
