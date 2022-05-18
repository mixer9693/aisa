package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.repository.CoffeeMachineRepository;

@Service
@RequiredArgsConstructor
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineRepository repository;

    @Override
    public void update(CoffeeMachine coffeeMachine) {
        repository.save(coffeeMachine);
    }

    @Override
    public Iterable<CoffeeMachine> getAll() {
        return repository.findAll();
    }
}
