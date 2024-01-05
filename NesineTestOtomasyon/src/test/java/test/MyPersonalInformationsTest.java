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
import org.openqa.selenium.WebElement;
import services.UserLogin;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/MyPersonalInformationsTest.feature")
public class MyPersonalInformationsTest extends AppiumSetup {

    private AppiumDriver<MobileElement> driver;

    private final By userNameText = By.id("com.pordiva.nesine.android:id/username_edit");
    private final By passwordText = By.id("com.pordiva.nesine.android:id/password_edit");
    private final By loginButton = By.id("com.pordiva.nesine.android:id/login_btn");
    private final By myAccount = By.id("com.pordiva.nesine.android:id/my_account_layout");
    private final By memberidValue = By.id("com.pordiva.nesine.android:id/memberidValue");
    private final By personelInfoButton = By.id("com.pordiva.nesine.android:id/personal_info_btn");

    private String memberidCode = null;


    @Before
    public void beforeScenario() {
        connectionSettings();
    }

    @Given("Kullanıcı uygulamaya giriş yapar")
    public void kullaniciUygulamayaGirisYapar() {
        UserLogin.performLogin(driver, userNameText, passwordText, loginButton, "valid_username", "valid_password");
    }

    @When("Anasayfa üzerinden üye numarası alınır")
    public void anasayfaUzerindenUyeNumarasiAlinir() {
        // üye numarasını al değişkene at
        WebElement memberidValueSelect = driver.findElement(memberidValue);
        memberidCode = memberidValueSelect.getText();
    }

    @When("Hesabım sayfasına yönlendirilir")
    public void hesabimSayfasinaYonlendirilir() {
        // Uygulamada Hesabım sayfasına gider
        WebElement myAccountSelect = driver.findElement(myAccount);
        myAccountSelect.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("Kişisel Bilgilerim butonuna tıklanır")
    public void kisiselBilgilerimButonunaTiklanir() {
        // Uygulamada Promosyonlarım butonuna tıklar
        WebElement personelInfoSelect = driver.findElement(personelInfoButton);
        personelInfoSelect.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Then("Üye numarasının doğru bir şekilde görüntülendiği kontrol edilir")
    public void uyeNumarasiDogruGoruntulendiKontrolEdilir() {
        // Üye numarasının kontrolü
        String displayedMemberNumber = driver.findElement(By.id("lblMemberId")).getText();
        assertEquals("Üye numarası doğru eşleşmedi", memberidCode, displayedMemberNumber);
    }
}
