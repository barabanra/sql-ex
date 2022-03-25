package ru.barabanra.sqlex.persistence.repository.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.mapper.ProductMapper;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.ProductJpaEntity;
import ru.barabanra.sqlex.persistence.repository.ProductRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String>, ProductRepository {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    List<ProductJpaEntity> findAllByTypeEquals(String type);

    @Override
    default List<ProductEntity> findALLBy(ProductFilter filter) {
        List<ProductJpaEntity> allBy = findAllByTypeEquals(filter.getType());
        return productMapper.mapEntity(allBy);
    }

    @Override
    default PersistenceType getType() {
        return PersistenceType.JPA;
    }
}