package com.appium.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class ScrollToTab {
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
	 public void ScrollToTab() throws InterruptedException {
		 
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
			
			//Click on Scrollable text element.
			driver.findElementByXPath("//android.widget.TextView[@content-desc='5. Scrollable']").click();
			
			
			 //Used for loop to scroll tabs until Tab 11 displayed.
			  for(int i=0; i<=10; i++){
			   //Set implicit wait to 2 seconds for fast horizontal scrolling.
			   driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);   
			   if(driver.findElements(By.xpath("//android.widget.TextView[@text='TAB 11']")).size()!= 0){
			    //If Tab 11 Is displayed then click on It.
			    System.out.println("Tab 11 has been found and now clicking on It.");
			    driver.findElement(By.xpath("//android.widget.TextView[@text='TAB 11']")).click();
			    break;
			   }else{
			    //If Tab 11 Is not displayed then scroll tabs from right to left direction by calling ScrollTabs() method.
			    ScrollTabs();
			   }
			  }
	
			
			//Set implicit wait to 15 seconds after horizontal scrolling.
			  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			  
			  //Locate parent element of text area.
			  WebElement ele1 = (WebElement) driver.findElements(By.id("android:id/tabcontent")).get(0);
			  //Locate text area of Tab 11 using It's parent element.
			  WebElement ele2 = ele1.findElement(By.className("android.widget.TextView"));
			  //Get text from text area of Tab 11 and print It In console.
			  System.out.println("Text under selected tab is -> "+ele2.getText());
			 } 
			 
			 //To scroll tabs right to left In horizontal direction.
			 public void ScrollTabs() {
			  //Get the size of screen.
			  size = driver.manage().window().getSize();  
			  
			  //Find swipe start and end point from screen's with and height.
			  //Find startx point which is at right side of screen.
			  int startx = (int) (size.width * 0.70);
			  //Find endx point which is at left side of screen.
			  int endx = (int) (size.width * 0.30);
			  //Set Y Coordinates of screen where tabs display.
			  int YCoordinates = 255;  

			  //Swipe tabs from Right to Left.
			  driver.swipe(startx, YCoordinates, endx, YCoordinates, 1000);  
			 }
			 
			 @AfterTest
			 public void End() {
			  driver.quit();
			 }
			
	}
