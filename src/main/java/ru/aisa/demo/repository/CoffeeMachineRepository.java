package ru.aisa.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.CoffeeMachine;

public interface CoffeeMachineRepository extends CrudRepository<CoffeeMachine, Integer> {
    @Query("SELECT c FROM CoffeeMachine c WHERE c.available = ?1")
    Iterable<CoffeeMachine> findByAvailable(boolean available);
}
