package ru.aisa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aisa.demo.entity.Refill;

public interface RefillRepository extends CrudRepository<Refill, Integer> {
}
