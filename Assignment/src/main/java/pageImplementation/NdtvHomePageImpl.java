package pageImplementation;

import org.openqa.selenium.*;
import utils.ElementUtil;

import java.util.List;


public class NdtvHomePageImpl {
	
	public WebDriver driver;
	private ElementUtil elementUtil;

	private static String moreSubmenuId="h_sub_menu";
	

	public NdtvHomePageImpl(WebDriver driver) {
		this.driver=driver;
		this.elementUtil=new ElementUtil(driver);
	}
	
	public void clickMoreSubMenu(){
		driver.findElement(By.id(moreSubmenuId)).click();
	}

	public void clickOnTab(String tabName){
		driver.findElement(By.linkText(tabName)).click();
	}


}
	
	
	


