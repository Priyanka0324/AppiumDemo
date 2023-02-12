package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ZoomOutDemo {

	
AndroidDriver<MobileElement> driver;  

	
	@BeforeTest
	public void launchApp() throws MalformedURLException {
		// 1) Launch the Google Maps 
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "RZ8N82V8VNY");
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("appPackage", "com.google.android.apps.maps");
		cap.setCapability("appActivity", "com.google.android.maps.MapsActivity");
		cap.setCapability("noReset", true);
		driver=new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
	}
	
	
	@Test
	public void ZoomOut() throws InterruptedException {
		
		Thread.sleep(3000);
		
		TouchAction<?> ta1 = new TouchAction<>(driver); 
		// Swipe from right to left
		ta1.press(PointOption.point(1056, 855)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(611, 913)).release();
		
		// Swipe from left to right
		TouchAction<?> ta2 = new TouchAction<>(driver); 
		ta2.press(PointOption.point(100, 921)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(453, 870)).release();
		
		MultiTouchAction mta = new MultiTouchAction (driver);
		mta.add(ta1).add(ta2).perform();
		
	}
	
}
