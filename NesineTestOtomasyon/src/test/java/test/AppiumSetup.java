package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumSetup {

    protected AppiumDriver<MobileElement> driver;
    protected WebDriverWait wait;

    public void connectionSettings() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("deviceName", "Pixel 4 API 33");
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("uuid", "emulator-5554");
            desiredCapabilities.setCapability("platformVersion", "13.0");
            desiredCapabilities.setCapability("appPackage", "com.pordiva.nesine.android");
            desiredCapabilities.setCapability("appActivity", "com.pordiva.nesine.android:id/someElementOnMainActivity");
            desiredCapabilities.setCapability("skipUnlock", "true");
            desiredCapabilities.setCapability("noReset", "false");

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            wait = new WebDriverWait(driver, 10);

        } catch (MalformedURLException malformedURLException) {
            System.out.println("Invalid URL: " + malformedURLException);
        }
    }

}
