package ru.aisa.demo.util;

import org.springframework.stereotype.Component;
import ru.aisa.demo.enums.Size;

@Component
public class TimeCalculator {
    private final int initTime = 3;
    private final int baseTimePer100ml = 4;

    public int calculate(Size size, Float speedFactor){
        return (int) (initTime + baseTimePer100ml * speedFactor * (size.getValue() / 100));
    }
}
