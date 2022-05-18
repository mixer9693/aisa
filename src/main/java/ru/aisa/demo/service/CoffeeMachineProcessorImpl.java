package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Product;
import ru.aisa.demo.enums.OrderStatus;
import ru.aisa.demo.util.CostCalculator;
import ru.aisa.demo.util.OrderCost;
import ru.aisa.demo.util.TimeCalculator;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoffeeMachineProcessorImpl implements CoffeeMachineProcessor {
    private final CostCalculator resourceCalculator;
    private final TimeCalculator timeCalculator;
    private final OrderService orderService;
    private final CoffeeMachineService coffeeMachineService;

    @Override
    public void executeOrder(Order order, CoffeeMachine coffeeMachine) {
        coffeeMachine.setAvailable(false);

        log.info("Try to execute order {}", order.getUid());
        orderService.updateStatus(order, OrderStatus.EXECUTING, "Your coffee will be done soon");

        OrderCost cost = resourceCalculator.calculate(order.getType(), order.getSize());

        if (!coffeeMachine.hasResources(cost)){
            log.info("Machine {} is not ready to execute order {}", coffeeMachine.getId(), order.getUid());
            orderService.updateStatus(order, OrderStatus.ERROR, "Machine is not available");
            return;
        }

        coffeeMachine.useResources(cost);
        int time = timeCalculator.calculate(order.getSize(), coffeeMachine.getSpeedFactor());
        log.info("Try to process order {} in {} sec", order.getUid(), time);
        try {
            Product product = process(order, time);
            order.setProduct(product);
            orderService.updateStatus(order, OrderStatus.COMPLETED, "Ok");
        } catch (RuntimeException e){
            orderService.updateStatus(order, OrderStatus.ERROR, "Error");
        } finally {
            coffeeMachine.setAvailable(true);
            coffeeMachineService.update(coffeeMachine);
        }
    }

    public Product process(Order order, int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.warn("Process was interrupted");
            throw new RuntimeException(e.getMessage());
        }
        return new Product.ProductBuilder()
                .size(order.getSize().getValue() + "ml")
                .type(order.getType().getName())
                .build();
    }

}
