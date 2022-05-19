package ru.aisa.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.aisa.demo.enums.Size;

@Component
public class TimeCalculator {
    @Value("${time_calculator.init_time}")
    private int initTime;
    @Value("${time_calculator.base_time_per_100_ml}")
    private int baseTimePer100ml;

    public int calculate(Size size, Float speedFactor){
        return (int) (initTime + baseTimePer100ml * speedFactor * (size.getValue() / 100));
    }
}
