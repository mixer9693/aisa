package ru.aisa.demo.entity;

import lombok.Data;
import ru.aisa.demo.util.OrderCost;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coffee_machines")
public class CoffeeMachine {
    @Id
    @SequenceGenerator(name = "coffee_machine_seq", sequenceName = "coffee_machine_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_machine_seq")
    private Integer id;
    private String name;
    private Integer cupsAmount;
    private Integer waterAmount;
    private Integer coffeeAmount;
    private Integer milkAmount;
    private Float speedFactor;
    private boolean available;

    public boolean hasResources(OrderCost cost){
        return getCoffeeAmount() >= cost.getCoffee()
                && getWaterAmount() >= cost.getWater()
                && getMilkAmount() >= cost.getMilk()
                && getCupsAmount() >= cost.getCups();
    }

    public void useResources(OrderCost cost){
        setWaterAmount(getWaterAmount() - cost.getWater());
        setCoffeeAmount(getCoffeeAmount() - cost.getCoffee());
        setMilkAmount(getMilkAmount() - cost.getMilk());
        setCupsAmount(getCupsAmount() - cost.getCups());
    }
}
