package com.automation.tests.day1;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
    private AndroidDriver<MobileElement> driver;

    @Test
    public void test1() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        //these 2 parameters are required if you want to automate existing application
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        WebElement digit1 = driver.findElement(By.id("com.android.calculator2:id/digit_1"));
        WebElement digit2 = getDigit(2);
        WebElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        WebElement equals = driver.findElementByAccessibilityId("equals");
        WebElement result = driver.findElementById("com.android.calculator2:id/result");

        WebElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
        WebElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));
        WebElement divide = driver.findElement(MobileBy.AccessibilityId("divide"));
        WebElement delete = driver.findElement(MobileBy.AccessibilityId("delete"));

        digit1.click();//click on 1
        digit2.click();//click on 2

        plus.click();//click on +

        digit2.click();//click on 2
        digit2.click();//click on 2

        equals.click();//click on equals
        String actual = result.getText();//get text of result
        Assert.assertEquals("34", actual);

        //2 * 2 = 4
        getDigit(2).click();//click on 2
        multiply.click();
        getDigit(2).click();//click on 2
        equals.click();
        Assert.assertEquals("4", result.getText());

        //10 - 5 + 6 = 11

        getDigit(1).click();
        getDigit(0).click();
        minus.click();
        getDigit(5).click();
        plus.click();
        getDigit(6).click();
        equals.click();

        //java.lang.ClassCastException: class org.openqa.selenium.remote.RemoteWebElement cannot be cast to class io.appium.java_client.MobileElement
// (org.openqa.selenium.remote.RemoteWebElement and io.appium.java_client.MobileElement are in unnamed module of loader 'app')
//        it means that we are trying to convert wem element into mobile element or action that we are trying to do is not available for mobile element
        boolean actualFormulaVisibilityStatus = Boolean.parseBoolean(driver.findElementById("com.android.calculator2:id/formula").getAttribute("displayed"));

        Assert.assertTrue("Formula is not visible!", actualFormulaVisibilityStatus);
        Assert.assertTrue("Result is not visible!", driver.findElementById("com.android.calculator2:id/result").isDisplayed());
        System.out.println(driver.findElementById("com.android.calculator2:id/formula").getAttribute("displayed"));

        Assert.assertEquals("11", result.getText());

        Thread.sleep(3000);
        driver.closeApp();
    }

    public WebElement getDigit(int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }
}
