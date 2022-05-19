package ru.aisa.demo.service;

import ru.aisa.demo.entity.CoffeeMachine;

public interface CoffeeMachineService {
    void update(CoffeeMachine coffeeMachine);
    Iterable<CoffeeMachine> getAll();
}
