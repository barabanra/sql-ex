package ru.barabanra.sqlex.service.impl;

import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.dto.service.ProductDto;
import ru.barabanra.sqlex.mapper.ProductMapper;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;
import ru.barabanra.sqlex.persistence.repository.ProductRepository;
import ru.barabanra.sqlex.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final Map<PersistenceType, ProductRepository> productRepositoryMap;

    public ProductServiceImpl(ProductMapper productMapper,
                              List<ProductRepository> productRepositories) {
        this.productMapper = productMapper;
        this.productRepositoryMap = buildProductRepositoryMap(productRepositories);
    }

    @Override
    public List<ProductDto> findAllBy(ProductFilter filter) {
        ProductRepository productRepository = productRepositoryMap.get(filter.getPersistenceType());
        if (productRepository == null) {
            return new ArrayList<>();
        }
        List<ProductEntity> allBy = productRepository.findALLBy(filter);
        return productMapper.mapDto(allBy);
    }

    private Map<PersistenceType, ProductRepository> buildProductRepositoryMap(List<ProductRepository> productRepositories) {
        return productRepositories
                .stream()
                .collect(Collectors.toMap(ProductRepository::getType, Function.identity()));
    }

}