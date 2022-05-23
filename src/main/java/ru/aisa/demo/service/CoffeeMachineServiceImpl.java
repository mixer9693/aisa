package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.enums.MachineState;
import ru.aisa.demo.repository.CoffeeMachineRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineRepository repository;

    @Override
    @Transactional
    public void update(CoffeeMachine coffeeMachine) {
        repository.save(coffeeMachine);
    }

    @Override
    public Iterable<CoffeeMachine> getAll() {
        return repository.findAll();
    }

    @Override
    public CoffeeMachine getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void updateState(CoffeeMachine machine, MachineState state) {
        machine.setState(state);
        repository.save(machine);
    }
}
