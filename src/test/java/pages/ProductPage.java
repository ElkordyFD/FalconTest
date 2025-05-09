package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage {

    private WebDriver driver;

    // Locators for product page elements
    private By menuButton = By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button"); // Menu button to open sidebar
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");

    // Filteration dropdown
    private By filterDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");

    // Footer social media links
    private By twitterIcon = By.className("social_twitter");
    private By facebookIcon = By.className("social_facebook");
    private By linkedinIcon = By.className("social_linkedin");

    // Product info locators (using class)
    private By productImage = By.xpath("//*[@id=\"item_4_img_link\"]/img");
    private By productName = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private By productPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/div");
    private By productDescription = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div");
    private By addToCartButton = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button[contains(@class, btn_primary)]");
    private By removeButton = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button[contains(@class, 'btn_secondary')]");

    private By shopIcon = By.className("svg-inline--fa");
    private By shopIconCount = By.className("fa-layers-counter shopping_cart_badge");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    //  Product
    public void clickProductImage() {
        driver.findElement(productImage).click();
    }
    public void clickProductName() {
        driver.findElement(productName).click();
    }
    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }
    public void clickRemoveButton() {
        driver.findElement(addToCartButton).click();
        driver.findElement(removeButton).click();
    }

    public boolean isProductDescriptionDisplayed() {
        return driver.findElement(productDescription).isDisplayed();
    }
    public boolean isProductPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }
    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(removeButton).isDisplayed();
    }
    public boolean isAddButtonDisplayed() {
        return driver.findElement(addToCartButton).isDisplayed();
    }

    //  Footer
    public void clickTwitterIcon() {
        driver.findElement(twitterIcon).click();
    }
    public void clickFacebookIcon() {
        driver.findElement(facebookIcon).click();
    }
    public void clickLinkedInIcon() {
        driver.findElement(linkedinIcon).click();
    }

    public void clickShopIcon() {
        driver.findElement(shopIcon).click();
    }

    // Select filter option by visible text (e.g., "Name (A to Z)")
    public void selectFilterOption(String visibleText) {
        WebElement dropdownElement = driver.findElement(filterDropdown);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    // Get the list of product names
    public List<String> getProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
