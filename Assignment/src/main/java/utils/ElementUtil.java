package utils;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ElementUtil {
	
	private WebDriverWait webDriverWait;
	private final WebDriver driver;
	
	public ElementUtil(final WebDriver driver) {
		this.webDriverWait=new WebDriverWait(driver, Long.parseLong(TestBase.propertyMap.get("defaultWait")));
		this.driver=driver;
	}
	
	
	
	public void waitForElement(By by) {
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	
	public void waitForDuration(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	

}
