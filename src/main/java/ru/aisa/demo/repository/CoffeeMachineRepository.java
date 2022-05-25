package ru.aisa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.CoffeeMachine;

public interface CoffeeMachineRepository extends CrudRepository<CoffeeMachine, Integer> {
}
