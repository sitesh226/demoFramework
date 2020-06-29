package tests.Phase1;


import base.TestBase;
import org.testng.annotations.Test;
import testConstants.TestConstants;

public class TestWeather extends TestBase {

	
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
		assertUtil.assertTrue(this.getPageFactory().getCityWeatherPageImpl().isCityDisplayedOnMap(TestConstants.WeatherPage.search_City),"Verify Searched city visible on map");
		assertUtil.assertAll();
	}

	@Test(description="Verify select City on map and validate weather info ",dependsOnMethods = "searchAndSelectCity")
	public void selectCityAndVerifyWeatherInfo() {
		testlogger.info("Select city on map");
		this.getPageFactory().getCityWeatherPageImpl().selectCityOnMap(TestConstants.WeatherPage.search_City);
		testlogger.info("Verify city weather info is displayed");
		assertUtil.assertTrue(this.getPageFactory().getCityWeatherPageImpl().isWeatherInfoDisplayed(),"Weather info popup displayed");
		assertUtil.assertNotNull(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("City").contains(TestConstants.WeatherPage.search_City));
		assertUtil.assertNotNull(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Condition "));
		assertUtil.assertNotNull(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Wind"));
		assertUtil.assertNotNull(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Temp in Degrees"));
		assertUtil.assertNotNull(this.getPageFactory().getCityWeatherPageImpl().getCityWeatherPopupDetails().get("Temp in Fahrenheit"));
		assertUtil.assertAll();
	}
}
