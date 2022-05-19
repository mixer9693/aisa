package ru.aisa.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coffee_types")
public class CoffeeType {
    @Id
    @SequenceGenerator(name = "coffee_type_seq", sequenceName = "coffee_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_type_seq")
    private Integer id;
    @Column(unique = true)
    private String name;
    private Integer waterConsumptionPer100ml;
    private Integer coffeeConsumptionPer100ml;
    private Integer milkConsumptionPer100ml;
}
