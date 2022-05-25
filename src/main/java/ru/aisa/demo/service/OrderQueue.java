package ru.aisa.demo.service;

import org.springframework.stereotype.Service;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.repository.OrderRepository;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class OrderQueue implements Queue<Order, String>{
    private final BlockingQueue<String> queue;
    private final OrderRepository repository;

    public OrderQueue(OrderRepository repository){
        this.repository = repository;
        this.queue = new ArrayBlockingQueue<>(10);
    }

    @Override
    public Order getNext() throws InterruptedException {
        String orderUid = queue.take();
        return repository.findById(orderUid).orElseThrow();
    }

    @Override
    public boolean hasNext(){
        return !queue.isEmpty();
    }

    @Override
    public void add(String uid) throws InterruptedException {
        boolean isSuccess = queue.offer(uid, 10, TimeUnit.SECONDS);
        if (!isSuccess){
            throw new RuntimeException("The queue is full");
        }
    }
}
