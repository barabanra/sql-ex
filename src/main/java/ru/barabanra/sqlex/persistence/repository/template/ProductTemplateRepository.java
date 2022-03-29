package ru.barabanra.sqlex.persistence.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ProductFilter;
import ru.barabanra.sqlex.mapper.ProductMapper;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;
import ru.barabanra.sqlex.persistence.entity.template.ProductTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductTemplateRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;

    @Override
    public List<ProductEntity> findALLBy(ProductFilter filter) {
        return jdbcTemplate.query(
                "SELECT model, maker, type from PRODUCT WHERE type = :type",
                Collections.singletonMap("type", filter.getType().name()),
                new BeanPropertyRowMapper<>(ProductTemplateEntity.class))
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TEMPLATE;
    }

}