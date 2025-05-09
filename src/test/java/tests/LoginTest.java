package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.LoginPage;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    // Setup method to open the browser before each test
    @BeforeMethod
    public void setUp() {
        // Set path for ChromeDriver (make sure to adjust the path or use WebDriverManager)

        WebDriverManager.chromedriver().setup();
        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();

        // Initialize the LoginPage with the driver
        loginPage = new LoginPage(driver);

        // Open the login page (adjust URL to your app)
        driver.get("https://www.saucedemo.com/v1/");
    }

    @Test
    public void testLoginWithStandardUser() {
        loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test
    public void testLoginWithProblemUser() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test
    public void testLoginWithLockedUser() {
        loginPage.loginAs("problem_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test
    public void testLoginWithGlitchUser() {
        loginPage.loginAs("performance_glitch_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test
    public void testInValidLoginWithEmptyPassword() {
        loginPage.loginAs("standard_user", "");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithInvalidPassword() {
        loginPage.loginAs("standard_user", "secret_sauce2");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithArabicPassword() {
        loginPage.loginAs("standard_user", "مصطفي");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceBeginningPassword() {
        loginPage.loginAs("standard_user", "   secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceMiddlePassword() {
        loginPage.loginAs("standard_user", "secret _ sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceEndPassword() {
        loginPage.loginAs("standard_user", "secret_sauce   ");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithOnlySpacesPassword() {
        loginPage.loginAs("standard_user", "                  ");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithEmptyUsername() {
        loginPage.loginAs("", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testLoginWithWrongUsername() {
        loginPage.loginAs("standard_user1", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithInvalidArabicUsername() {
        loginPage.loginAs("مصطفي", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceBeginningUsername() {
        loginPage.loginAs("    standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceMiddleUsername() {
        loginPage.loginAs("standard _ user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithSpaceEndUsername() {
        loginPage.loginAs("standard_user     ", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLoginWithOnlySpacesUsername() {
        loginPage.loginAs("            ", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void testInValidLogin() {
        loginPage.loginAs("standard_user", "secret");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
