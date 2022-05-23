package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.util.ResourceSet;

@Service
@RequiredArgsConstructor
@Log4j2
public class SingleMachineController {
    private final Queue<Order, String> ordersQueue;
    private final Queue<Refill, Integer> serviceQueue;

    private final CoffeeMachineProcessor processor;
    private final CoffeeMachineService machineService;
    private CoffeeMachine machine;

    @Value("${current_machine_id}")
    private Integer currentMachineId;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        init();

        log.info("SingleMachineController starting");
        while (machine != null){
            log.info("SingleMachineController work");
            try {
                work();
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        log.info("SingleMachineController finished");
    }

    public void init(){
        machine = machineService.getById(currentMachineId);
    }

    public void work() throws InterruptedException {
        if (serviceQueue.hasNext() || machine.isUnusable()){
            Refill refill = serviceQueue.getNext();
            processor.serve(machine, refill);
        }

        Order order = ordersQueue.getNext();
        processor.executeOrder(machine, order);
    }

}
