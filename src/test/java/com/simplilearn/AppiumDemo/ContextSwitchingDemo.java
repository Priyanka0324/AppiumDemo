package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ContextSwitchingDemo {

	AndroidDriver<MobileElement> driver;  //initializing the driver as Android driver

	@BeforeTest
	public void LaunchBrowser() throws MalformedURLException {

		// 1) Launch the eBay browser
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName", "RZ8N82V8VNY");
		dc.setCapability("platformName", "ANDROID");
		dc.setCapability("browserName", "Chrome");
		dc.setCapability("noReset", true);
		driver=new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), dc);


		// 2) Navigate to https://ebay.com
		driver.get("https://ebay.com");		

	}

	@Test
	public void addShortcutToHomeScreen() {
		//Default context
		Set<String> contexts= driver.getContextHandles();
		for (String t:contexts) {
			System.out.println(t);
		}
		System.out.println("Current context = " + driver.getContext());

		//Switch to NAtive APP context
		driver.context("NATIVE_APP");

		// 3) Click on 3 dots at top right corner
		driver.findElement(By.id("com.android.chrome:id/menu_button")).click();

		// 4) Click on 'Add to Home screen' in the context menu				
		driver.findElement(By.xpath("//android.widget.TextView[@text='Desktop site']")).click();
		driver.findElement(By.id("com.android.chrome:id/menu_button")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Add to Home screen']")).click();
		
		// 5) Wait for the pop-up to appear	
		WebDriverWait wait = new WebDriverWait(driver,60);		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/positive_button")));
		
		// 6) click on 'Add' button on the pop-up		
		driver.findElement(By.id("com.android.chrome:id/positive_button")).click();

		// 7) click on 'Add to Home screen' button
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.sec.android.app.launcher:id/add_item_add_button")));
		driver.findElement(By.id("com.sec.android.app.launcher:id/add_item_add_button")).click();

	}

	@AfterTest
	public void closeApp() {
		driver.quit();
	}
		
}
