package ru.aisa.demo.util;

import org.springframework.stereotype.Component;
import ru.aisa.demo.entity.CoffeeType;
import ru.aisa.demo.enums.Size;

@Component
public class CostCalculator {

    public ResourceSet calculate(CoffeeType type, Size size){
        return new ResourceSet.ResourceSetBuilder()
                .water(type.getWaterConsumptionPer100ml() * (size.getValue() / 100))
                .coffee(type.getCoffeeConsumptionPer100ml() * (size.getValue() / 100))
                .milk(type.getMilkConsumptionPer100ml() * (size.getValue() / 100))
                .cups(1)
                .build();
    }
}
