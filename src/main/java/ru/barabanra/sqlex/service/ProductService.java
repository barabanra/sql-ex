package ru.barabanra.sqlex.service;

import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.dto.service.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAllBy(ProductFilter filter);

}