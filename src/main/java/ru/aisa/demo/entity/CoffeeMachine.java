package ru.aisa.demo.entity;

import lombok.Data;
import ru.aisa.demo.enums.MachineState;
import ru.aisa.demo.util.ResourceSet;

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
    @Enumerated(EnumType.STRING)
    private MachineState state;

    public boolean hasResources(ResourceSet cost){
        return getCoffeeAmount() >= cost.getCoffee()
                && getWaterAmount() >= cost.getWater()
                && getMilkAmount() >= cost.getMilk()
                && getCupsAmount() >= cost.getCups();
    }

    public void useResources(ResourceSet cost){
        setWaterAmount(getWaterAmount() - cost.getWater());
        setCoffeeAmount(getCoffeeAmount() - cost.getCoffee());
        setMilkAmount(getMilkAmount() - cost.getMilk());
        setCupsAmount(getCupsAmount() - cost.getCups());
    }

    public void replenishResources(ResourceSet resources){
        setWaterAmount(getWaterAmount() + resources.getWater());
        setCoffeeAmount(getCoffeeAmount() + resources.getCoffee());
        setMilkAmount(getMilkAmount() + resources.getMilk());
        setCupsAmount(getCupsAmount() + resources.getCups());
    }

    public boolean isUnusable(){
        return state == MachineState.UNUSABLE;
    }

    public boolean isAvailable(){
        return state == MachineState.AVAILABLE;
    }
}
