package tests.Phase3;


import Pojo.CityWeather;
import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import testConstants.TestConstants;

import static io.restassured.RestAssured.given;

public class TestWeatherComparison extends TestBase {

	
	@Test(description="Verify city can be pinned on weather map")
	public void gotoWeatherPage() {
		testlogger.info("Click on More submenu");
		this.getPageFactory().getNdtvHomePageImpl().clickMoreSubMenu();
		testlogger.info("Click on Weather tab");
		this.getPageFactory().getNdtvHomePageImpl().clickOnTab(TestConstants.NdtvHomePage.weatherTab);
		assertUtil.assertTrue(this.getPageFactory().getCityWeatherPageImpl().isThisCityWeatherPage(),"Verify navigated to weather page");
		assertUtil.assertAll();
	}


	@Test(description="Verify Search and select city ",dependsOnMethods = "gotoWeatherPage")
	public void searchAndSelectCity() {
        testlogger.info("Search and select city");
        this.getPageFactory().getCityWeatherPageImpl().searchAndSelectCity(TestConstants.WeatherPage.search_City);
		testlogger.info("Verify searched city is displayed on map");
		assertUtil.assertTrue(this.getPageFactory().getCityWeatherPageImpl().isCityDisplayedOnMap(TestConstants.WeatherPage.search_City),"Verfiy searched city displayed");
		assertUtil.assertAll();
	}

	@Test(description="Verify temperature on ui and api ",dependsOnMethods = "searchAndSelectCity")
	public void verifyTemperatureBetweenUiAndApi() {
		testlogger.info("Select city on map");
		this.getPageFactory().getCityWeatherPageImpl().selectCityOnMap(TestConstants.WeatherPage.search_City);
		testlogger.info("Verify city weather info is displayed");
		assertUtil.assertTrue(this.getPageFactory().getCityWeatherPageImpl().isWeatherInfoDisplayed(),"Weather info popup displayed");
		double tempOnUi=Double.parseDouble(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Temp in Degrees"));
		int humidityOnUI=Integer.parseInt(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Humidity").replaceAll(" ","").replaceAll("%",""));
		Temperature uiTemperatureObject=new Temperature(tempOnUi,humidityOnUI);
		testlogger.info("Hit api to get temperature and humidity");
		testlogger.info("Hit weather  api for: "+TestConstants.WeatherPage.search_City);
		RequestSpecification requestSpecification=given()
				.accept(ContentType.JSON)
				.when()
				.queryParam("q",TestConstants.WeatherPage.search_City)
				.queryParam("appid",TestConstants.TestWeatherComparison.appId);

		testlogger.info("Weather api request: "+((RequestSpecificationImpl) requestSpecification).getURI());
		Response response=requestSpecification.get();
		CityWeather cityWeather=response.as(CityWeather.class);
		testlogger.info("Weather api response: "+cityWeather);
		double tempApi=convertKelvinToCelsius(cityWeather.getMain().getTemp());
		int humidityApi=cityWeather.getMain().getHumidity();
		testlogger.info("Ui Temperature :"+tempOnUi+ ", Humidity on UI:"+humidityOnUI);
		testlogger.info("Api Temperature :"+tempApi+ ", Humidity on UI:"+humidityApi);
		Temperature apiTemperatureObject=new Temperature(tempApi,humidityApi);
		TemperatureComparator temperatureComparator=new TemperatureComparator();
		int comparisonResult=temperatureComparator.compare(uiTemperatureObject,apiTemperatureObject);
		if(comparisonResult==0)
			assertUtil.assertTrue(true,"UI and api temperature within variance limit of "+TestConstants.TemperatureComparator.temperatureVariance);
		else if(comparisonResult==1){
			assertUtil.assertTrue(false,"UI and api temperature difference greater than variance of "+TestConstants.TemperatureComparator.temperatureVariance);

			assertUtil.assertAll();
		}



	}


	public double convertKelvinToCelsius(double tempInKelvin){
		return tempInKelvin-273.15;

	}

}
