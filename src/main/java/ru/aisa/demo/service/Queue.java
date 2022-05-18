package ru.aisa.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service
public class Queue {
    private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10, true);

    public String take() throws InterruptedException {
        return queue.take();
    }

    public void add(String uuid){
        queue.add(uuid);
    }

}
