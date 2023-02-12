package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Calculator {

	AndroidDriver<MobileElement> driver;  //initializing the driver as Android driver

	@BeforeTest
	public void launchApplication() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();

		// Real device name or virtual device name
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "RZ8N82V8VNY");
		cap.setCapability("platformName", "ANDROID");

		//cap.setCapability("ignoreHiddenApiPolicyError", true);// For real device

		// Get the packageName and Activity from manifest.xml and 
		cap.setCapability("appPackage", "com.miui.calculator");

		//find Activity>intent-filter>MAIN>name of the action
		cap.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");

		// This is needed because every time we need to agree for the application so in order to not reset and start again
		cap.setCapability("noReset", true); 

		//Appium server host and port no. => 0.0.0.0 is the ip address of local machine
		driver=new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
	}


	@Test(priority =0)
	public void verifyAdditionProcess() {

		// Clear the previous calculations

		driver.findElement(By.id("com.miui.calculator:id/btn_c_s")).click();
		driver.findElement(By.id("com.miui.calculator:id/btn_c_s")).click();

		// Press digit 9
		driver.findElement(By.id("com.miui.calculator:id/digit_9")).click();

		//Press + symbol
		driver.findElementByAccessibilityId("plus").click();

		//Press digit 6
		driver.findElement(By.id("com.miui.calculator:id/digit_6")).click();

		//Press '=' button
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"equals\"]")).click();

		String expRes = "15";
		String ActRes = driver.findElement(By.id("com.miui.calculator:id/result")).getText().substring(2, 4);
		Assert.assertEquals(ActRes, expRes);		
	}

	@Test(priority =1)
	public void verifyMultipicationProcess() {

		//Press digit 4
		driver.findElement(By.id("com.miui.calculator:id/digit_4")).click();

		//Press *
		driver.findElement(By.id("com.miui.calculator:id/op_mul")).click();

		//Press digit 5
		driver.findElement(By.id("com.miui.calculator:id/digit_5")).click();

		//Press '='
		driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

		String ExpRes="20";
		String ActRes = driver.findElement(By.id("com.miui.calculator:id/result")).getText().substring(2, 4);
		Assert.assertEquals(ActRes,ExpRes);
	}

	@Test(priority =2)
	public void verifyDelBtnIsPresent() {
		boolean DelBtnIsPresent= driver.findElement(By.id("com.miui.calculator:id/btn_del_s")).isDisplayed();
		Assert.assertTrue(DelBtnIsPresent);
	}



	@AfterTest
	public void CloseApp() {
		driver.quit();
	}



}
