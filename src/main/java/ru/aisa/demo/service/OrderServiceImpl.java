package ru.aisa.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import ru.aisa.demo.dto.OrderInputDto;
import ru.aisa.demo.dto.OrderOutputDto;
import ru.aisa.demo.dto.ProductDto;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.entity.Product;
import ru.aisa.demo.enums.OrderStatus;
import ru.aisa.demo.mapper.OrderMapper;
import ru.aisa.demo.mapper.ProductMapper;
import ru.aisa.demo.repository.CoffeeTypeRepository;
import ru.aisa.demo.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CoffeeTypeRepository coffeeTypeRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final Queue queue;
    private final PlatformTransactionManager transactionManager;

    @Override
    public OrderOutputDto create(OrderInputDto orderDto){
        Order order = orderMapper.dtoToOrder(orderDto, coffeeTypeRepository);
        order.setStatus(OrderStatus.WAITING);

        TransactionStatus transaction = transactionManager.getTransaction(null);
        try {
            orderRepository.save(order);
            transactionManager.commit(transaction);
        } catch (Throwable e) {
            transactionManager.rollback(transaction);
            throw new RuntimeException(e);
        }

        queue.add(order.getUid());

        return orderMapper.orderToDto(order);
    }

    @Override
    public OrderOutputDto get(String uuid){
        Order order = orderRepository.findById(uuid).orElseThrow();
        return orderMapper.orderToDto(order);
    }

    @Override
    @Transactional
    public ProductDto obtainProduct(String uuid){
        Order order = orderRepository.findById(uuid).orElseThrow();
        if (order.getStatus() != OrderStatus.COMPLETED){
            throw new NoSuchElementException();
        }
        Product product = order.getProduct();
        product.setObtained(true);
        order.setStatus(OrderStatus.OBTAINED);
        orderRepository.save(order);

        return productMapper.productToDto(product);
    }

    @Override
    @Transactional
    public void updateStatus(Order order, OrderStatus status, String comment) {
        order.setStatus(status);
        order.setComment(comment);
        orderRepository.save(order);
    }
}
