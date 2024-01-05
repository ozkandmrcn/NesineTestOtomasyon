package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import services.PageControl;
import services.UserLogin;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/LoginUnsuccessTest.feature")
public class LoginUnSuccesTest extends AppiumSetup {

    private AndroidDriver<MobileElement> driver;

    private final By mainLoginButton = By.id("com.pordiva.nesine.android:id/btn_login");
    private final By userNameText = By.id("com.pordiva.nesine.android:id/username_edit");
    private final By passwordText = By.id("com.pordiva.nesine.android:id/password_edit");
    private final By loginButton = By.id("com.pordiva.nesine.android:id/login_btn");


    @Before
    public void beforeScenario() {
        connectionSettings();
    }

    @Given("Kullanıcı uygulama ana sayfasında")
    public void kullaniciUygulamaAnaSayfasinda() {
        boolean isMainActivityLoaded = PageControl.isMainActivityDefaultLoaded(driver);
        assertTrue(isMainActivityLoaded, "Kullanıcı Anasayfaya yönlendirilemedi");
    }

    @When("Kullanıcı Giriş Yap butonuna tıklar")
    public void kullaniciGirisYapButonunaTiklar() {
        WebElement mainLoginSelect = driver.findElement(mainLoginButton);
        mainLoginSelect.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("Kullanıcı kullanıcı adı ve şifre alanlarını doldurur")
    public void kullaniciKullaniciAdiVeSifreAlanlariniDoldurur() {
        Properties properties= UserLogin.loadPropertiesFile("config.properties");

        String username = properties.getProperty("invalid_username");
        String password = properties.getProperty("invalid_password");

        WebElement userNameSelect = driver.findElement(userNameText);
        userNameSelect.sendKeys(username);

        WebElement passwordTextSelect = driver.findElement(passwordText);
        passwordTextSelect.sendKeys(password);
    }

    @When("Kullanıcı Giriş Yap butonuna tıklar")
    public void kullaniciGirisYapButonunaTiklarTekrar() {
        WebElement loginSelect = driver.findElement(loginButton);
        loginSelect.click();
    }

    @Then("Kullanıcı Girişi hatalı popup görür")
    public void kullaniciGirisiHatalıPopupCıkar() {
        Assert.assertTrue(driver.findElement(By.id("android:id/message")).isDisplayed(), "Üye numaranız, TC kimlik numaranız veya şifreniz hatalıdır! Lütfen bilgilerinizi kontrol ediniz.");

    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
}
