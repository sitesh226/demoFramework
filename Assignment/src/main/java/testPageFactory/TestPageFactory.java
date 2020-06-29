package testPageFactory;

import org.openqa.selenium.WebDriver;
import pageImplementation.CityWeatherPageImpl;
import pageImplementation.NdtvHomePageImpl;

public class TestPageFactory {
	private final WebDriver driver;
	
	private NdtvHomePageImpl ndtvHomePageImpl;
	private CityWeatherPageImpl cityWeatherPageImpl;

	public TestPageFactory(final WebDriver driver) {
		this.driver=driver;
	}
	
	public NdtvHomePageImpl getNdtvHomePageImpl() {
		if(this.ndtvHomePageImpl ==null)
			this.ndtvHomePageImpl =new NdtvHomePageImpl(driver);
		
		return this.ndtvHomePageImpl;
	}

	public CityWeatherPageImpl getCityWeatherPageImpl() {
		if(this.cityWeatherPageImpl ==null)
			this.cityWeatherPageImpl =new CityWeatherPageImpl(driver);

		return this.cityWeatherPageImpl;
	}

}
