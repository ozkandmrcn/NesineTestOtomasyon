package services;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.MobileElement;

public class UserLogin {

    public static void performLogin(AppiumDriver<MobileElement> driver, By userNameText, By passwordText, By loginButton, String usernameKey, String passwordKey) {

        Properties properties = loadPropertiesFile("config.properties");

        String username = properties.getProperty(usernameKey);
        String password = properties.getProperty(passwordKey);

        WebElement userNameSelect = driver.findElement(userNameText);
        userNameSelect.sendKeys(username);

        WebElement passwordTextSelect = driver.findElement(passwordText);
        passwordTextSelect.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement loginSelect2 = driver.findElement(loginButton);
        loginSelect2.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
        public static Properties loadPropertiesFile(String fileName) {
            Properties properties = new Properties();
            try (InputStream input = UserLogin.class.getClassLoader().getResourceAsStream(fileName)) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        }

}
