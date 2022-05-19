package ru.aisa.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aisa.demo.dto.ProductDto;
import ru.aisa.demo.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "type", target = "coffee")
    @Mapping(source = "creationDateTime", target = "created")
    ProductDto productToDto(Product product);
}
