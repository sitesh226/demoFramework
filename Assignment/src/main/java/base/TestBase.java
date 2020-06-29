package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.*;
import org.testng.annotations.*;
import testPageFactory.TestPageFactory;
import utils.CustomAssert;
import utils.ElementUtil;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class TestBase {

	
	public static Map<String,String> propertyMap;
	public  WebDriver driver;
	private TestPageFactory pageFactory;
	private ElementUtil elementUtil;
	static ExtentReports extent;
	protected static ExtentTest testlogger;
	protected CustomAssert assertUtil;


	static {
		try {
			propertyMap=getEnvironmentProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void createReporter(){
		ExtentManager.createReporter();
		extent=ExtentManager.getExtent();
	}
	

	
		@BeforeClass(alwaysRun = true)
		@Parameters({"browserParam"})
		public  WebDriver createDriverAndHitUrl(@Optional String browserParam) {

			String browser="";
			if(browserParam==null)
				browser=propertyMap.get("browser");
			
			else
				browser=browserParam;
			
			String urlToHit=propertyMap.get("url");
			
			switch(browser) {
			
				case "Chrome":{
					System.setProperty("webdriver.chrome.driver","./src/main/resources/drivers/windows/chromedriver.exe");
				//	Map<String, Object> prefs = new HashMap();
				//	prefs.put("profile.default_content_setting_values.notifications", 2);
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					driver = new ChromeDriver(options);
					//driver=new ChromeDriver();
					break;
	
					
				}
				
				case "Firefox":{
					System.setProperty("webdriver.gecko.driver","./src/main/resources/drivers/windows/geckodriver.exe" );
					FirefoxOptions option = new FirefoxOptions();
					option.addPreference("dom.webnotifications.enabled", false);
					option.addPreference("app.update.enabled", false);
					option.addPreference("geo.enabled", false);
					driver = new FirefoxDriver(option);
				//	driver=new FirefoxDriver();
					break;
					
				}
			
			}

			
			driver.navigate().to(urlToHit);  
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

			pageFactory=new TestPageFactory(driver);
			elementUtil=new ElementUtil(driver);
			apiSetUp();

			return driver;
			
		}

		@AfterClass(alwaysRun = true)
		public void afterClass(){
		//CLose current window.
			driver.close();
		}
		


	public static void apiSetUp() {
		baseURI="http://api.openweathermap.org";
		basePath="/data/2.5/weather";
	}

		@BeforeMethod
		public synchronized void beforeMethod(Method method){
			assertUtil=new CustomAssert();
			testlogger =extent.createTest(method.getName());
		}

		@AfterMethod(alwaysRun = true)
		public synchronized void afterMethod(ITestResult iTestResult){

			if(iTestResult.getStatus()==ITestResult.SUCCESS){
				testlogger.pass(iTestResult.getMethod().getMethodName() +"   Passed");
			}else if(iTestResult.getStatus()==ITestResult.FAILURE)
				testlogger.fail(iTestResult.getMethod().getMethodName() +"   Failed with "+ iTestResult.getThrowable().getMessage());
			else if(iTestResult.getStatus()==ITestResult.SKIP)
				testlogger.skip(iTestResult.getMethod().getMethodName() +"   Skipped");


			extent.flush();


		}


	

		public WebDriver getDriver(){
		    return driver;
		}
		
		
		
		
		public void close(){
		    driver.close();
		}
		
		
		@AfterSuite(alwaysRun = true)
		public void quit() {
//			if(!(((FirefoxDriver) driver).getSessionStorage()==null))
//				driver.quit();
		}
		
		public static Map<String, String> getEnvironmentProperties() throws IOException {
			//Read properties
			FileReader propertyFile=new FileReader("./src/main/resources/config/environmentProperties.properties");
			Properties properties= new Properties();
			properties.load(propertyFile);
			
			Map<String,String> propertyMap=new HashMap<String, String>();
			
			for (Entry<Object, Object> entry : properties.entrySet()) {
			    propertyMap.put((String) entry.getKey(), (String) entry.getValue());
			}
			return propertyMap;
		}
		
		
		public TestPageFactory getPageFactory() {
			return this.pageFactory;
		}
		
		public ElementUtil getElementUtil() {
			return this.elementUtil;
		}


		public static ExtentTest getTestlogger(){
			return testlogger;
		}


}
