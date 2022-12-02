package ru.netology.testing.appium;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumChangeTextTest {

    private AppiumDriver<?> driver;
    private final String TEXT = "Hello Yuri";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        //desiredCapabilities.setCapability("appium:deviceName", "Samsung");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6 API 30");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");


        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void checkEmptyTextInputTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.sendKeys(TEXT);
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el3.click();
        el1.click();
        el1.sendKeys("    ");
        el3.click();
        MobileElement el7 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el7.click();
        assertEquals(TEXT, el7.getText());
    }

    @Test
    public void checkNewActivityTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.sendKeys(TEXT);
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el4.click();
        assertEquals(TEXT, el4.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
