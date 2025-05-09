package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    // Locators
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By cancelButton = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/a");
    private By continueButton = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input");

    // Footer social media links
    private By twitterIcon = By.className("social_twitter");
    private By facebookIcon = By.className("social_facebook");
    private By linkedinIcon = By.className("social_linkedin");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
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

    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        clickContinue();
    }
}
