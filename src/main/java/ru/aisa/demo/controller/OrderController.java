package ru.aisa.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aisa.demo.dto.OrderInputDto;
import ru.aisa.demo.dto.OrderOutputDto;
import ru.aisa.demo.dto.ProductDto;
import ru.aisa.demo.service.OrderService;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderOutputDto create(@RequestBody OrderInputDto orderDto){
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFoundException(NoSuchElementException e){
        return e.getMessage();
    }

}
