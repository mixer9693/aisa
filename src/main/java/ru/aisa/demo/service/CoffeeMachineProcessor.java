package ru.aisa.demo.service;

import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Refill;

public interface CoffeeMachineProcessor {
    void serve(CoffeeMachine machine, Refill resources);
    void executeOrder(CoffeeMachine machine, Order order);
}
