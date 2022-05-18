package ru.aisa.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.aisa.demo.enums.OrderStatus;
import ru.aisa.demo.enums.Size;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GenericGenerator(name = "order_id_gen", strategy = "ru.aisa.demo.util.OrderIdGenerator")
    @GeneratedValue(generator = "order_id_gen")
    private String uid;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private CoffeeType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime statusUpdated;

    private String comment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public void setStatus(OrderStatus status){
        this.status = status;
        this.statusUpdated = LocalDateTime.now();
    }
}
