package ru.aisa.demo.service;

import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.repository.RefillRepository;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceQueue implements Queue<Refill, Integer> {
    private final BlockingQueue<Integer> queue;
    private final RefillRepository repository;

    public ServiceQueue(RefillRepository repository){
        this.repository = repository;
        this.queue = new ArrayBlockingQueue<>(1);
    }

    @Override
    public boolean hasNext() {
        System.out.println("serv queue size " +queue.size());
        return !queue.isEmpty();
    }

    @Override
    public Refill getNext() throws InterruptedException {
        Integer id = queue.take();
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void add(Integer key) throws InterruptedException {
        boolean isSuccess = queue.offer(key, 10, TimeUnit.SECONDS);
        if (!isSuccess){
            throw new RuntimeException();
        }
    }
}
