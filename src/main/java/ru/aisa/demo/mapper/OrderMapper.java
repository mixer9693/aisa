package ru.aisa.demo.mapper;

import org.mapstruct.*;
import ru.aisa.demo.dto.OrderInputDto;
import ru.aisa.demo.dto.OrderOutputDto;
import ru.aisa.demo.entity.CoffeeType;
import ru.aisa.demo.entity.Order;
import ru.aisa.demo.repository.CoffeeTypeRepository;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "type.name", target = "name")
    @Mapping(source = "statusUpdated", target = "updated")
    OrderOutputDto orderToDto(Order order);

    @Mapping(target = "type", ignore = true)
    Order dtoToOrder(OrderInputDto dto, @Context CoffeeTypeRepository repo);

    @AfterMapping
    default void dtoToOrder(@MappingTarget Order order, OrderInputDto dto, @Context CoffeeTypeRepository repo) {
        CoffeeType type = repo.findByName(dto.getName()).orElseThrow();
        order.setType(type);
    }
}
