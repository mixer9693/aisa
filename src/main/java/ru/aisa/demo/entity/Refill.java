package ru.aisa.demo.entity;

import lombok.Data;
import ru.aisa.demo.enums.RefillStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "refills")
@Data
public class Refill {
    @Id
    @SequenceGenerator(name = "maintenance_seq", sequenceName = "maintenance_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_seq")
    private Integer id;
    private Integer water;
    private Integer coffee;
    private Integer milk;
    private Integer cups;
    @Enumerated(EnumType.STRING)
    private RefillStatus status;
    private LocalDateTime statusUpdated;
    @ManyToOne
    @JoinColumn(name = "coffee_machine_id")
    private CoffeeMachine coffeeMachine;

    public void setStatus(RefillStatus status) {
        this.status = status;
        this.statusUpdated = LocalDateTime.now();
    }
}
