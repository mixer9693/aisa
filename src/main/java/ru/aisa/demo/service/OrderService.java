package ru.aisa.demo.service;

import ru.aisa.demo.dto.OrderInputDto;
import ru.aisa.demo.dto.OrderOutputDto;
import ru.aisa.demo.dto.ProductDto;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.enums.OrderStatus;

public interface OrderService {
    OrderOutputDto create(OrderInputDto orderDto);
    OrderOutputDto get(String uuid);
    ProductDto obtainProduct(String uuid);
    void updateStatus(Order order, OrderStatus status, String comment);
}
