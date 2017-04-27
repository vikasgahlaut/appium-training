package com.appium.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorTest {
	WebDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		// Created object of DesiredCapabilities class.
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Set android deviceName desired capability. Set your device name.
		capabilities.setCapability("deviceName", "emulator-5554");

		// Set BROWSER_NAME desired capability. It's Android in our case here.
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		// Set android VERSION desired capability. Set your mobile device's OS
		// version.
		capabilities.setCapability(CapabilityType.VERSION, "7.1.1");

		// Set android platformName desired capability. It's Android in our case
		capabilities.setCapability("platformName", "Android");

		// Set android appPackage desired capability. It is
		// com.android.calculator2 for calculator application.
		// Set your application's appPackage if you are using any other app.
		capabilities.setCapability("appPackage", "com.android.calculator2");

		// Set android appActivity desired capability. It is
		// com.android.calculator2.Calculator for calculator application.
		// Set your application's appPackage if you are using any other app.
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

		// Created object of RemoteWebDriver will all set capabilities.
		// Set appium server address and port number in URL string.
		// It will launch calculator app in android device.
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Sum() {
		// Click on DELETE/CLR button to clear result text box before running
		// test.
		driver.findElements(By.id("del")).get(0).click();

		// Click on number 2 button.
		driver.findElement(By.id("digit_2")).click();

		// Click on + button.
		driver.findElement(By.id("op_add")).click();

		// Click on number 5 button.
		driver.findElement(By.id("digit_5")).click();

		// Click on = button.
		driver.findElement(By.id("eq")).click();

		// Get result from result text box.
		String result = driver.findElement(By.id("result")).getText();
		System.out.println("Number sum result is : " + result);

	}
}
