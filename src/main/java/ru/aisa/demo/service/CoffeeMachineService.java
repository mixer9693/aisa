package ru.aisa.demo.service;

import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.enums.MachineState;

public interface CoffeeMachineService {
    void update(CoffeeMachine coffeeMachine);
    Iterable<CoffeeMachine> getAll();
    CoffeeMachine getById(Integer id);
    void updateState(CoffeeMachine machine, MachineState state);
}
