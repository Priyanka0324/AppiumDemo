package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DockerDemo {
	
	WebDriver driver;
	
	@BeforeTest
	public void launch() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		driver.get("https://in.linkedin.com/");
	}
	
	
	
	@Test
	public void verifyTitle() {
		String expectedTitle = "LinkedIn:Log In or Sign Up";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	

	@AfterTest
	public void close() {
		driver.quit();
	}
}
