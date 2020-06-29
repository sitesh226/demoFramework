package Pojo;

public class Wind
{
    private int deg;

    private Double speed;

    public int getDeg ()
    {
        return deg;
    }

    public void setDeg (int deg)
    {
        this.deg = deg;
    }

    public Double getSpeed ()
    {
        return speed;
    }

    public void setSpeed (Double speed)
    {
        this.speed = speed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deg = "+deg+", speed = "+speed+"]";
    }
}

