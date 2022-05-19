package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class Executor {
    private final CoffeeMachineProcessor machineProcessor;
    private final OrderRepository orderRepository;
    private final CoffeeMachineService coffeeMachineService;
    private final Queue queue;

    private CoffeeMachine coffeeMachine;

    @PostConstruct
    public void postConstruct(){
        coffeeMachine = coffeeMachineService.getAll().iterator().next();
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        while (coffeeMachine.isAvailable()){
            try {
                String orderUid = queue.take();

                Optional<Order> optional = orderRepository.findById(orderUid);
                if (optional.isEmpty()){
                    log.warn("Order {} not found", orderUid);
                    continue;
                }
                Order order = optional.get();
                machineProcessor.executeOrder(order, coffeeMachine);

            } catch (Exception e){
                log.warn(e.getMessage());
            }
        }
    }

}
