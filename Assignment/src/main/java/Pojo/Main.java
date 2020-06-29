package Pojo;

public class Main
{
    private Double temp;

    private Double temp_min;

    private int humidity;

    private int pressure;

    private Double feels_like;

    private Double temp_max;

    public Double getTemp ()
    {
        return temp;
    }

    public void setTemp (Double temp)
    {
        this.temp = temp;
    }

    public Double getTemp_min ()
    {
        return temp_min;
    }

    public void setTemp_min (Double temp_min)
    {
        this.temp_min = temp_min;
    }

    public int getHumidity ()
    {
        return humidity;
    }

    public void setHumidity (int humidity)
    {
        this.humidity = humidity;
    }

    public int getPressure ()
    {
        return pressure;
    }

    public void setPressure (int pressure)
    {
        this.pressure = pressure;
    }

    public Double getFeels_like ()
    {
        return feels_like;
    }

    public void setFeels_like (Double feels_like)
    {
        this.feels_like = feels_like;
    }

    public Double getTemp_max ()
    {
        return temp_max;
    }

    public void setTemp_max (Double temp_max)
    {
        this.temp_max = temp_max;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [temp = "+temp+", temp_min = "+temp_min+", humidity = "+humidity+", pressure = "+pressure+", feels_like = "+feels_like+", temp_max = "+temp_max+"]";
    }
}
