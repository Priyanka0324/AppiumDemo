package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeDemo {
	
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
	public void swipeFromLeftToRight() {
		TouchAction<?> ta = new TouchAction<>(driver);  // ? is like wildcard characterstics
		ta.press(PointOption.point(354, 649)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
			.moveTo(PointOption.point(832, 777)).release().perform();
	}
	
	
}
