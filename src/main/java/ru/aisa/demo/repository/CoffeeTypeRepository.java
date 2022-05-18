package ru.aisa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.CoffeeType;

import java.util.Optional;

public interface CoffeeTypeRepository extends CrudRepository<CoffeeType, Integer> {
    Optional<CoffeeType> findByName(String name);
}
