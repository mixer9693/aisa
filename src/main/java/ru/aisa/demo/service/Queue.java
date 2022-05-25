package ru.aisa.demo.service;

public interface Queue<T, K> {
    boolean hasNext();
    T getNext() throws InterruptedException;
    void add(K key) throws InterruptedException;
}
