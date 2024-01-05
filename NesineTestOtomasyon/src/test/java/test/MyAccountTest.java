package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import services.UserLogin;


import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/MyAccountTest.feature")
public class MyAccountTest extends AppiumSetup {

    private AppiumDriver<MobileElement> driver;

    private final By userNameText = By.id("com.pordiva.nesine.android:id/username_edit");
    private final By passwordText = By.id("com.pordiva.nesine.android:id/password_edit");
    private final By loginButton = By.id("com.pordiva.nesine.android:id/login_btn");
    private final By myAccount = By.id("com.pordiva.nesine.android:id/my_account_layout");
    private final By promotionsButton = By.id("com.pordiva.nesine.android:id/my_promotions_btn");
    private final By promotionsCodeText = By.id("code");
    private final By securityCodeText = By.id("securityCode");
    private final By closeButton = By.id("com.pordiva.nesine.android:id/back");


    @Before
    public void beforeScenario() {
        connectionSettings();
    }

    @Given("Kullanıcı uygulamaya giriş yapar")
    public void kullaniciUygulamayaGirisYapar() {
        UserLogin.performLogin(driver,userNameText, passwordText,loginButton,"valid_username", "valid_password");
    }

    @Given("Hesabım sayfasına yönlendirilir")
    public void hesabimSayfasinaYonlendirilir() {
        // Uygulamada Hesabım sayfasına gider
        WebElement myAccountSelect = driver.findElement(myAccount);
        myAccountSelect.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("Promosyonlarım butonuna tıklanır")
    public void promosyonlarimButonunaTiklanir() {
        // Uygulamada Promosyonlarım butonuna tıklar
        WebElement promotionsSelect = driver.findElement(promotionsButton);
        promotionsSelect.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("Promosyon Kodu için rastgele bir sayı girişi yapılır ve Girilen sayının doğruluğu kontrol edilir")
    public void promosyonKoduIcinRastgeleSayiGirisiYapilir() {
        WebElement promotionsCode = driver.findElement(promotionsCodeText);
        promotionsCode.sendKeys("1234567890");
    }

    @When("Güvenlik Kodu doğru bir şekilde doldurulur")
    public void güvenlikKoduGirisiYapılır() {
        WebElement securityCode = driver.findElement(securityCodeText);
        securityCode.sendKeys("YDF7K");
    }

    @When("Kapat butonuna tıklanır ve tekrar Hesabım sayfasına dönüldüğü kontrol edilir")
    public void kapatButonunaTiklanirVeTekrarHesabimSayfasinaDonulduğuKontrolEdilir() {
        WebElement closeSelect = driver.findElement(closeButton);
        closeSelect.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Then("Hesabım sayfasına dönüldüğü kontrol edilir")
    public void hesabimSayfasinaDonulduguKontrolEdilir() {
        assertTrue(isMainActivityDefaultLoaded(), "Kullanıcı Hesabım sayfasına yönlendirilemedi");
    }


    private boolean isMainActivityDefaultLoaded() {
        try {
            WebElement element = driver.findElement(By.id("com.pordiva.nesine.android:id/my_account_layout"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
