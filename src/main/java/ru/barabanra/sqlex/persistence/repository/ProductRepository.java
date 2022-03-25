package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {

    PersistenceType getType();

    List<ProductEntity> findALLBy(ProductFilter filter);

}