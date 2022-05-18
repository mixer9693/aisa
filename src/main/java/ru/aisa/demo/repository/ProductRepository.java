package ru.aisa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
