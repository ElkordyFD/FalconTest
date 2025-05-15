package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
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

    @Test(groups = "Happy Scenario")
    public void testLoginWithStandardUser() {
        loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test(groups = "Happy Scenario")
    public void testLoginWithProblemUser() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test(groups = "Happy Scenario")
    public void testLoginWithLockedUser() {
        loginPage.loginAs("problem_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test(groups = "Happy Scenario")
    public void testLoginWithGlitchUser() {
        loginPage.loginAs("performance_glitch_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithEmptyPassword() {
        loginPage.loginAs("standard_user", "");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithWrongPassword() {
        loginPage.loginAs("standard_user", "secret_sauce2");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithArabicPassword() {
        loginPage.loginAs("standard_user", "مصطفي");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithSpaceBeginningPassword() {
        loginPage.loginAs("standard_user", "   secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithSpaceMiddlePassword() {
        loginPage.loginAs("standard_user", "secret _ sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithSpaceEndPassword() {
        loginPage.loginAs("standard_user", "secret_sauce   ");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Password")
    public void testLoginWithOnlySpacesPassword() {
        loginPage.loginAs("standard_user", "                  ");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithEmptyUsername() {
        loginPage.loginAs("", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithWrongUsername() {
        loginPage.loginAs("standard_user1", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithInvalidArabicUsername() {
        loginPage.loginAs("مصطفي", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithSpaceBeginningUsername() {
        loginPage.loginAs("    standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithSpaceMiddleUsername() {
        loginPage.loginAs("standard _ user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithSpaceEndUsername() {
        loginPage.loginAs("standard_user     ", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = "Invalid Username")
    public void testLoginWithOnlySpacesUsername() {
        loginPage.loginAs("            ", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @Test(groups = {"Invalid Username","Invalid Password"})
    public void testLoginWithReplaceingFields() {
        loginPage.loginAs("secret_sauce", "standard_user");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/") && !loginPage.getErrorMessage().isEmpty());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
