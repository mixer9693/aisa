package ru.aisa.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aisa.demo.dto.OrderInputDto;
import ru.aisa.demo.dto.OrderOutputDto;
import ru.aisa.demo.dto.ProductDto;
import ru.aisa.demo.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderOutputDto create(@RequestBody OrderInputDto orderDto) {
        return orderService.create(orderDto);
    }

    @GetMapping("/orders/{id}")
    public OrderOutputDto get(@PathVariable("id") String uuid){
        return orderService.get(uuid);
    }

    @GetMapping("/orders/{id}/products")
    public ProductDto obtainProduct(@PathVariable("id") String uuid){
        return orderService.obtainProduct(uuid);
    }

}
