package ru.barabanra.sqlex.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.dto.response.ProductResponseDto;
import ru.barabanra.sqlex.dto.service.ProductDto;
import ru.barabanra.sqlex.mapper.ProductMapper;
import ru.barabanra.sqlex.service.ProductService;

import java.util.List;

@RestController
@Tag(name = "Products")
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @Operation(description = "Получение продуктов")
    public List<ProductResponseDto> findAllBy(ProductFilter filter) {
        List<ProductDto> allBy = productService.findAllBy(filter);
        return productMapper.map(allBy);
    }

}