package pageImplementation;

import org.openqa.selenium.*;
import utils.ElementUtil;

import java.util.HashMap;


public class CityWeatherPageImpl {

	public WebDriver driver;
	private ElementUtil elementUtil;

	private static String searchBoxId="searchBox";
	private static String cityText="//div[@class='cityText' and contains(text(),'%s')]";
	private static String weatherInfoPopup="//div[@class='leaflet-popup-content']";
	private static String cityScreenIdentifier="//div[@class='infoHolder' and contains(text(),'Current weather conditions in your city.')]";
	private static String weatherInfoPopupElements="//span[@class='heading')]";



	public CityWeatherPageImpl(WebDriver driver) {
		this.driver=driver;
		this.elementUtil=new ElementUtil(driver);
	}

	public boolean isThisCityWeatherPage(){
		try{
			WebElement weatherPageIdentifier=driver.findElement(By.xpath(cityScreenIdentifier));
			return weatherPageIdentifier.isDisplayed();
		}catch (NoSuchElementException|TimeoutException ex){
			return false;
		}
	}

	public void searchCity(String city){
		driver.findElement(By.id(searchBoxId)).sendKeys(city);
	}

	public void searchAndSelectCity(String city){
		searchCity(city);
		if(!driver.findElement(By.id(city)).isSelected())
			driver.findElement(By.id(city)).click();

	}

	public boolean isCityDisplayedOnMap(String city){
		try{
			WebElement searchedCity=driver.findElement(By.xpath(String.format(cityText,city)));
			return searchedCity.isDisplayed();
		}catch (NoSuchElementException|TimeoutException ex){
			return false;
		}
	}

	public void selectCityOnMap(String city){
		WebElement searchedCity=driver.findElement(By.xpath(String.format(cityText,city)));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", searchedCity);
	}

	public boolean isWeatherInfoDisplayed(){
		try{
			WebElement weatherInfoPopupElement=driver.findElement(By.xpath(weatherInfoPopup));
			return weatherInfoPopupElement.isDisplayed();
		}catch (NoSuchElementException| TimeoutException ex) {
			return false;
		}
	}

	public HashMap<String,String> getCityWeatherPopupDetails(){
		String[] weatherInfo=driver.findElement(By.xpath(weatherInfoPopup+"/div")).getText().split("\n");
		HashMap<String,String> cityWeatherMap=new HashMap<>();
		cityWeatherMap.put("City",weatherInfo[0]);
		cityWeatherMap.put(weatherInfo[1].split(":")[0],weatherInfo[1].split(":")[1]);
		cityWeatherMap.put(weatherInfo[2].split(":")[0],weatherInfo[2].split(":")[1]);
		cityWeatherMap.put(weatherInfo[3].split(":")[0],weatherInfo[3].split(":")[1]);
		cityWeatherMap.put(weatherInfo[4].split(":")[0],weatherInfo[4].split(":")[1]);
		cityWeatherMap.put(weatherInfo[5].split(":")[0],weatherInfo[5].split(":")[1]);
		return cityWeatherMap;

	}
}
	
	
	


