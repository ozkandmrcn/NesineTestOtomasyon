package services;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PageControl {

    public static boolean isMainActivityDefaultLoaded(AndroidDriver<MobileElement> driver) {
        try {
            WebElement element = driver.findElement(By.id("com.pordiva.nesine.android:id/someElementOnMainActivity"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
