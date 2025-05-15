package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By product = By.className("cart_item");
    private By productQuantity = By.className("cart_quantity");
    private By productName = By.className("inventory_item_name");
    private By productDescription = By.className("inventory_item_desc");
    private By productPrice = By.className("inventory_item_price");
    private By removeButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button");  // scoped within item
    private By checkoutButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]");
    private By continueShoppingButton = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]");

    // Footer social media links
    private By twitterIcon = By.className("social_twitter");
    private By facebookIcon = By.className("social_facebook");
    private By linkedinIcon = By.className("social_linkedin");

    // Menu
    private By menuButton = By.className("bm-burger-button"); // Menu button to open sidebar
    private By sideBar = By.className("bm-menu");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By closedButton = By.className("bm-cross-button");

    // Product Info
    public String getProductName() {
        return driver.findElement(productName).getText();
    }
    public String getProductQuantity() {
        return driver.findElement(productQuantity).getText();
    }
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }
    public void clickRemoveButton() {
        driver.findElement(product).findElement(removeButton).click();
    }
    public boolean isProductRemovedFromCart() {
        try {
            return !driver.findElement(product).isDisplayed(); // true = removed
        } catch (NoSuchElementException e) {
            return true; // If not found at all, assume it was removed
        }
    }


    // Buttons
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }


    // Footer Icons'
    public void clickTwitterIcon() {
        driver.findElement(twitterIcon).click();
    }
    public void clickFacebookIcon() {
        driver.findElement(facebookIcon).click();
    }
    public void clickLinkedInIcon() {
        driver.findElement(linkedinIcon).click();
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
}
