package com.appium.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class ScrollingToText {

	AndroidDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {

		// Set Drag-Sort Demos app folder path. This statement will refer
		// project's folder path.
		File classpathRoot = new File(System.getProperty("user.dir"));

		// Set folder name "Apps" where .apk file is stored.
		File appDir = new File(classpathRoot, "/Apps");

		// Set Drag-Sort Demos .apk file name.
		File app = new File(appDir, "ApiDemos.apk");

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

		// Set .apk file's path capabilities.
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void ScrollToText() throws InterruptedException {

		// Scroll till element which contains "Views" text If It Is not visible
		// on screen.
		driver.scrollTo("Views");

		// Click on Views/.
		WebElement view = (WebElement) driver.findElementsById("android:id/text1").get(10);
		view.click();

		// Scroll till element which contains Tabs text.
		driver.scrollTo("Tabs");

		// Click on Tabs.

		driver.findElementByXPath("//android.widget.TextView[@text='Tabs']").click();

		Thread.sleep(10000);

	}

	@AfterTest
	public void End() {
		driver.quit();
	}

}
