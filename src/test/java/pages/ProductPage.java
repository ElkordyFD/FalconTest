package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Menu
    private By menuButton = By.className("bm-burger-button"); // Menu button to open sidebar
    private By sideBar = By.className("bm-menu");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By closedButton = By.className("bm-cross-button");

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
    private By addToCartButtonAtProductPage = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button[contains(@class, btn_primary)]");
    private By addToCartButtonAtProductDetailsPage = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button");
    private By removeButtonAtProductPage = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button[contains(@class, 'btn_secondary')]");
    private By removeButtonAtProductDetailsPage = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button");

    private By shopIcon  = By.className("svg-inline--fa");
    private By productAtCartPage = By.className("cart_item");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    // Menu
    public void clickOnMenuButton() {
        driver.findElement(menuButton).click();
    }
    public boolean isMenuSideBarDisplayed() {
        return driver.findElement(sideBar).isDisplayed();
    }
    public void clickOnAllItemsLink() {
        driver.findElement(allItemsLink).click();
    }
    public void clickOnAboutLink() {
        driver.findElement(aboutLink).click();
    }
    public void clickOnLogoutLink() {
        driver.findElement(logoutLink).click();
    }
    public void clickOnResetAppState() {
        driver.findElement(resetAppStateLink).click();
    }
    public void closeMenu() {
        driver.findElement(closedButton).click();
    }

    //  Product
    public void clickProductImage() {
        driver.findElement(productImage).click();
    }
    public void clickProductName() {
        driver.findElement(productName).click();
    }
    public void clickAddToCartAtProductPage() {
        driver.findElement(addToCartButtonAtProductPage).click();
    }
    public void clickAddToCartAtProductDetailsPage() {
        driver.findElement(addToCartButtonAtProductDetailsPage).click();
    }
    public void clickRemoveButton() {
        driver.findElement(removeButtonAtProductPage).click();
    }
    public void clickRemoveButtonAtProductDetailsPage() {
        driver.findElement(removeButtonAtProductDetailsPage).click();
    }
    public boolean isProductDescriptionDisplayed() {
        return driver.findElement(productDescription).isDisplayed();
    }
    public boolean isProductPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }
    public boolean isProductAddedToCartPage () {
        return driver.findElement(productAtCartPage).isDisplayed();
    }
    public boolean isProductRemovedFromCart() {
        try {
            return !driver.findElement(productAtCartPage).isDisplayed(); // true = removed
        } catch (NoSuchElementException e) {
            return true; // If not found at all, assume it was removed
        }
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

    // Cart
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
