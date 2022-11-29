package ru.netology.testing.appium;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumChangeTextTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Samsung");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");


        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void checkEmptyTextInputTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el2.sendKeys("Hello Yuri");
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el5.sendKeys("    ");
        MobileElement el6 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el6.click();
        MobileElement el7 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el7.click();
        assertEquals("Hello Yuri", el7.getText());
    }

    @Test
    public void checkNewActivityTest(){
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.sendKeys("Hello Yuri");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el4.click();
        assertEquals("Hello Yuri", el4.getText());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
