package com.test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class Notepad {
	
	 WindowsDriver driver;
	
	@BeforeClass
	public void startUp() throws IOException, InterruptedException {
		Desktop desktop = Desktop.getDesktop();
		desktop.open(new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe"));
		Thread.sleep(2000);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
		driver = new WindowsDriver(new URL("http://127.0.0.1:4723"),cap);
		System.out.println(driver.getSessionId());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test 
	public void testOne() {
		driver.findElementByName("Text Editor").sendKeys("Steparrow");
		driver.findElementByName("Format").click();
		driver.findElementByName("Font...").click();
		driver.findElementByName("Font:").equals(null);
		driver.findElementByName("Corbel").click();
		driver.findElementByName("Font style:").equals(null);
		driver.findElementByName("Bold").click();
		driver.findElementByName("Size:").equals(null);
		driver.findElementByName("36").click();
		driver.findElementByName("OK").click();
		driver.findElementByName("File").click();
		driver.findElementByAccessibilityId("3").click();
		driver.findElementByAccessibilityId("1001").sendKeys("steparrow");
		driver.findElementByName("Save").click();
	}
	
	@org.testng.annotations.AfterClass
	public void closeUp() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
	}
}