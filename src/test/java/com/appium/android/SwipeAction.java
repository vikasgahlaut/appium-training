package com.appium.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

//http://www.software-testing-tutorials-automation.com/2015/11/swipe-element-using-touchaction-class.html

public class SwipeAction {

	AndroidDriver driver;
	Dimension size;

	@BeforeTest
	public void setUp() throws MalformedURLException {

		// Set Drag-Sort Demos app folder path. This statement will refer
		// project's folder path.
		File classpathRoot = new File(System.getProperty("user.dir"));

		// Set folder name "Apps" where .apk file is stored.
		File appDir = new File(classpathRoot, "/Apps");

		// Set Drag-Sort Demos .apk file name.
		File app = new File(appDir, "SwipeListView Demo_v1.13_apkpure.com.apk");

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

		capabilities.setCapability("appPackage", "com.fortysevendeg.android.swipelistview");
		capabilities.setCapability("appActivity",
				"com.fortysevendeg.android.swipelistview.sample.activities.SwipeListViewExampleActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.RelativeLayout")));

	}

	@Test
	public void swipingHorizontal() throws InterruptedException {
		// Get the size of screen.
		size = driver.manage().window().getSize();
		System.out.println(size);

		// Find swipe x points from screen's with and height.
		// Find x1 point which is at right side of screen.
		int x1 = (int) (size.width * 0.20);
		// Find x2 point which is at left side of screen.
		int x2 = (int) (size.width * 0.80);

		// Create object of TouchAction class.
		TouchAction action = new TouchAction((AndroidDriver) driver);

		// Find element to swipe from right to left.
		WebElement ele1 = (WebElement) driver.findElementsById("com.fortysevendeg.android.swipelistview:id/front")
				.get(2);
		// Create swipe action chain and perform horizontal(right to left)
		// swipe.
		// Here swipe to point x1 Is at left side of screen. So It will swipe
		// element from right to left.
		action.longPress(ele1).moveTo(x1, 995).release().perform();

		// Find element to swipe from left to right.
		WebElement ele2 = (WebElement) driver.findElementsById("com.fortysevendeg.android.swipelistview:id/back")
				.get(2);
		// Create swipe action chain and perform horizontal(left to right)
		// swipe.
		// Here swipe to point x2 Is at right side of screen. So It will swipe
		// element from left to right.
		action.longPress(ele2).moveTo(x2, 995).release().perform();
	}

	@AfterTest
	public void End() {
		driver.quit();
	}

}
