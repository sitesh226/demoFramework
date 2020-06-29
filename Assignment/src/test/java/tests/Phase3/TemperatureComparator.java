package tests.Phase3;

import testConstants.TestConstants;

import java.util.Comparator;

public class TemperatureComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Temperature t1=(Temperature)o1;
        Temperature t2=(Temperature)o2;
        double tempDifference=Math.abs(t1.temperature-t2.temperature);

        if(tempDifference<=TestConstants.TemperatureComparator.temperatureVariance)
            return 0;
        else if(tempDifference> TestConstants.TemperatureComparator.temperatureVariance)
            return 1;
        else
            return -1;
    }
}
