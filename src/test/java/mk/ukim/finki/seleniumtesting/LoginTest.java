package mk.ukim.finki.seleniumtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class LoginTest {
    private WebDriver driver;
    private final String chromedriverPath = "C:\\Users\\User\\Desktop\\chromedriver.exe";

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }

    @Test//T1
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("aizhan.uristembek@alumni.nu.edu.kz", "wrong_password_test");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The password you’ve entered is incorrect. Forgot Password?");

    }

    @Test//T2
    public void canNotLoginWithoutUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The email or mobile number you entered isn’t connected to an account. Find your account and log in.");
    }

    @Test//T3
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("aizhan.uristembek@alumni.nu.edu.kz", "correct_password");
        assertTrue(new HomePage(driver).isLoaded());
    }


    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
