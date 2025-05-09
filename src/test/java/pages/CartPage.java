package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By cartItem = By.className("cart_list");
    private final By productQuantity = By.className("cart_quantity");
    private final By productName = By.className("inventory_item_name");
    private final By productDescription = By.className("inventory_item_desc");
    private final By productPrice = By.className("inventory_item_price");
    private final By removeButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button");  // scoped within item
    private final By checkoutButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]");
    private final By continueShoppingButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]");

    // Footer social media links
    private By twitterIcon = By.className("social_twitter");
    private By facebookIcon = By.className("social_facebook");
    private By linkedinIcon = By.className("social_linkedin");


    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    // Get product quantity
    public String getProductQuantity() {
        return driver.findElement(productQuantity).getText();
    }

    // Get product description
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    // Get product price (with dollar currency)
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    // Click the Remove button
    public void clickRemoveButton() {
        driver.findElement(cartItem).findElement(removeButton).click();
    }

    // Click the Checkout button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Click the Continue Shopping button
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public void clickTwitterIcon() {
        driver.findElement(twitterIcon).click();
    }

    public void clickFacebookIcon() {
        driver.findElement(facebookIcon).click();
    }

    public void clickLinkedInIcon() {
        driver.findElement(linkedinIcon).click();
    }

}
