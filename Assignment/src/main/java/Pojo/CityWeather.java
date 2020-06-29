package Pojo;

public class CityWeather
{
    private int visibility;

    private int timezone;

    private Main main;

    private Clouds clouds;

    private Sys sys;

    private long dt;

    private Coord coord;

    private Weather[] weather;

    private String name;

    private int cod;

    private int id;

    private String base;

    private Wind wind;

    public int getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (int visibility)
    {
        this.visibility = visibility;
    }

    public int getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (int timezone)
    {
        this.timezone = timezone;
    }

    public Main getMain ()
    {
        return main;
    }

    public void setMain (Main main)
    {
        this.main = main;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public long getDt ()
    {
        return dt;
    }

    public void setDt (long dt)
    {
        this.dt = dt;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getCod ()
    {
        return cod;
    }

    public void setCod (int cod)
    {
        this.cod = cod;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getBase ()
    {
        return base;
    }

    public void setBase (String base)
    {
        this.base = base;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [visibility = "+visibility+", timezone = "+timezone+", main = "+main+", clouds = "+clouds+", sys = "+sys+", dt = "+dt+", coord = "+coord+", weather = "+weather+", name = "+name+", cod = "+cod+", id = "+id+", base = "+base+", wind = "+wind+"]";
    }
}

