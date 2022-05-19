package ru.aisa.demo.service;

import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Product;

public interface CoffeeMachineProcessor {
    void executeOrder(Order order, CoffeeMachine coffeeMachine);
    Product process(Order order, int time);
}
