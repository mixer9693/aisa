package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Product;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.enums.MachineState;
import ru.aisa.demo.enums.OrderStatus;
import ru.aisa.demo.enums.RefillStatus;
import ru.aisa.demo.mapper.RefillMapper;
import ru.aisa.demo.repository.ProductRepository;
import ru.aisa.demo.util.CostCalculator;
import ru.aisa.demo.util.ResourceSet;
import ru.aisa.demo.util.TimeCalculator;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoffeeMachineProcessorImpl implements CoffeeMachineProcessor {
    private final CoffeeMachineService machineService;
    private final CostCalculator resourceCalculator;
    private final TimeCalculator timeCalculator;
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final RefillMapper refillMapper;
    private final RefillService refillService;

    @Override
    public void serve(CoffeeMachine machine, Refill refill){
        log.info("Try to serve machine {}", machine.getId());
        machineService.updateState(machine, MachineState.SERVICE);
        refillService.updateStatus(refill, RefillStatus.EXECUTING);

        ResourceSet resources = refillMapper.refillToResourceSet(refill);
        machine.replenishResources(resources);
        machineService.update(machine);

        refillService.updateStatus(refill, RefillStatus.COMPLETED);
        machineService.updateState(machine, MachineState.AVAILABLE);
        log.info("fin to serve refill {}", refill.getId());
    }

    @Override
    public void executeOrder(CoffeeMachine machine, Order order){
        machineService.updateState(machine, MachineState.PRODUCTION);

        log.info("Try to execute order {}", order.getUid());
        orderService.updateStatus(order, OrderStatus.EXECUTING, "Your coffee will be done soon");

        ResourceSet cost = resourceCalculator.calculate(order.getType(), order.getSize());
        if (!machine.hasResources(cost)){
            log.info("Machine {} isn't ready to execute order {}", machine.getId(), order.getUid());
            orderService.updateStatus(order, OrderStatus.ERROR, "Machine isn't available");
            machineService.updateState(machine, MachineState.UNUSABLE);
            return;
        }

        machine.useResources(cost);
        int time = timeCalculator.calculate(order.getSize(), machine.getSpeedFactor());
        log.info("Try to process order {} in {} sec", order.getUid(), time);
        try {
            Product product = makeProduct(order, time);
            order.setProduct(product);
            productRepository.save(product);
            orderService.updateStatus(order, OrderStatus.COMPLETED, "Enjoy your coffee");
        } catch (RuntimeException e){
            orderService.updateStatus(order, OrderStatus.ERROR, "Error");
        } finally {
            machineService.updateState(machine, MachineState.AVAILABLE);
        }
    }

    public Product makeProduct(Order order, int time){
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            log.warn("Processing order {} was interrupted", order.getUid());
            throw new RuntimeException(e.getMessage());
        }
        return new Product.ProductBuilder()
                .size(order.getSize().getValue() + "ml")
                .type(order.getType().getName())
                .build();
    }
}
