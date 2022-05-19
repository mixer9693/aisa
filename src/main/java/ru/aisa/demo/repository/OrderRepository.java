package ru.aisa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.Order;

public interface OrderRepository extends CrudRepository<Order, String> {
}
