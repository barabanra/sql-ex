package ru.barabanra.sqlex.persistence.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.mapper.ProductMapper;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;
import ru.barabanra.sqlex.persistence.repository.ProductRepository;
import ru.barabanra.sqlex.tables.Product;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductJooqRepository implements ProductRepository {

    private final DSLContext dslContext;
    private final ProductMapper productMapper;

    @Override
    public List<ProductEntity> findALLBy(ProductFilter filter) {
        return dslContext.selectFrom(Product.PRODUCT)
                .where(Product.PRODUCT.TYPE.eq(filter.getType().name()))
                .fetchInto(Product.PRODUCT)
                .stream()
                .map(productMapper::mapEntityFromJooq)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JOOQ;
    }

}