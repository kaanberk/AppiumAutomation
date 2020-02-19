package com.automation.tests.day2;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class EtsyTests {
    private AppiumDriver driver;
    //my etsy account
    private String email = "areatha@uspeakw.com";
    private String password = "Cybertek2020";

    @Test
    public void test1() throws Exception{
        /**
         * This code to run etst tests locally not in the cloud
         * {
         *       "platformName": "Android",
         *       "platformVersion": "7.0",
         *       "deviceName": "Pixel_2",
         *       "automationName": "UiAutomator2",
         *       "app": "https://cybertek-appium.s3.amazonaws.com/etsy.apk"
         *        }
         */
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "7.0");
        desiredCapabilities.setCapability("deviceName", "Pixel_2");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        //appium will automatically install app before testing
        desiredCapabilities.setCapability("app", "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        //wait for get started button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.etsy.android:id/btn_link")));
        WebElement getStarted = driver.findElement(By.id("com.etsy.android:id/btn_link"));
        getStarted.click();
        //to resolve NoSuchElementException, we need to add explicit wait
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.etsy.android:id/edit_username")));
        //username input box
        WebElement usernameElement = driver.findElementById("com.etsy.android:id/edit_username");
        //password input box
        WebElement passwordElement = driver.findElementById("com.etsy.android:id/edit_password");
        //sign in button
        WebElement signinElement = driver.findElementById("com.etsy.android:id/button_signin");

        usernameElement.sendKeys(email);
        passwordElement.sendKeys(password);
        signinElement.click();


        Thread.sleep(10000);
        driver.closeApp();
        driver.quit();

    }
}