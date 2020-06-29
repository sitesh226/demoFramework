package tests.Phase2;

import Pojo.CityWeather;
import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;



public class TestWeatherApi extends TestBase {

    CityWeather cityWeather;
    String cityName;



    @Test(description = "Verify response status from weather api",dataProvider = "cityProvider")
    public void testGetWeather(String city,String appId)  {
        cityName=city;
        testlogger.info("Hit weather  api for: "+city);
       RequestSpecification requestSpecification=given()
                .accept(ContentType.JSON)
                .when()
                .queryParam("q",city)
                .queryParam("appid",appId);

        testlogger.info("Weather api request: "+((RequestSpecificationImpl) requestSpecification).getURI());
        Response response=requestSpecification.get();
        cityWeather=response.as(CityWeather.class);
        testlogger.info("Weather api response: "+cityWeather);
        assertUtil.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,"Response code correct");
        assertUtil.assertAll();

    }

    @Test(description = "Verify visibility ,timezone ,dt,name,cod,id and base in city weather api response")
    public void verifyCityWeatherApiResponse(){
        testlogger.info("Verify visibility ,timezone ,dt,name,cod,id and base in city weather api response");
        assertUtil.assertNotNull(cityWeather.getBase(),"Verify base");
        assertUtil.assertNotNull(cityWeather.getVisibility(),"Verify Visibility");
        assertUtil.assertNotNull(cityWeather.getTimezone(),"Verify Timezone");
        assertUtil.assertNotNull(cityWeather.getDt(),"Verify Dt");
        assertUtil.assertEquals(cityWeather.getName(),cityName,"Verify City name correct");
        assertUtil.assertAll();
    }

    @Test(description = "Verify coord  ,weather,wind and clouds in city weather api response")
    public void verifyCoordAndWeatherApiResponse(){
        testlogger.info("Verify coord  ,weather,wind and clouds in city weather api response");
        assertUtil.assertNotNull(cityWeather.getCoord().getLat(),"Verify Coord lat");
        assertUtil.assertNotNull(cityWeather.getCoord().getLon(),"Verify Coord lon");
        assertUtil.assertNotNull(cityWeather.getWeather()[0].getDescription(),"Verify weather description");
        assertUtil.assertNotNull(cityWeather.getWeather()[0].getIcon(),"Verify weather icon");
        assertUtil.assertNotNull(cityWeather.getWeather()[0].getId(),"Verify weather id");
        assertUtil.assertNotNull(cityWeather.getWeather()[0].getMain(),"Verify weather main");

        assertUtil.assertNotNull(cityWeather.getWind().getDeg(),"Verify wind degree");
        assertUtil.assertNotNull(cityWeather.getWind().getSpeed(),"Verify wind speed");

        assertUtil.assertNotNull(cityWeather.getClouds().getAll(),"Verify clouds");
        assertUtil.assertAll();
    }

    @Test(description = "Verify sys  and main in city weather api response")
    public void verifySysAndMain(){
        assertUtil.assertNotNull(cityWeather.getMain().getFeels_like(),"Verify main feelsLIke");
        assertUtil.assertNotNull(cityWeather.getMain().getHumidity(),"Verify main humidity");
        assertUtil.assertNotNull(cityWeather.getMain().getPressure(),"Verify main pressure");
        assertUtil.assertNotNull(cityWeather.getMain().getTemp(),"Verify main temp");
        assertUtil.assertNotNull(cityWeather.getMain().getTemp_max(),"Verify main tempMax");
        assertUtil.assertNotNull(cityWeather.getMain().getTemp_min(),"Verify main tempMin");

        assertUtil.assertNotNull(cityWeather.getSys().getCountry(),"Verify sys Country");
        assertUtil.assertNotNull(cityWeather.getSys().getId(),"Verify sys id");
        assertUtil.assertNotNull(cityWeather.getSys().getSunrise(),"Verify sys sunrise");
        assertUtil.assertNotNull(cityWeather.getSys().getSunset(),"Verify sys sunset");
        assertUtil.assertNotNull(cityWeather.getSys().getType(),"Verify sys type");
        assertUtil.assertAll();
    }

    @DataProvider(name="cityProvider")
    public Object[][] cityProvider(){
        return new Object[][]{
                {"Kolkata","7fe67bf08c80ded756e598d6f8fedaea"}
        };
    }
}
