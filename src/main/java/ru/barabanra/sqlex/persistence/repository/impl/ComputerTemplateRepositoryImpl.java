package ru.barabanra.sqlex.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerTemplateRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ComputerTemplateRepositoryImpl implements ComputerTemplateRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ComputerTemplateEntity> findAllByPriceLessThanEqual(BigDecimal priceLessThan) {
        return jdbcTemplate.query(
                "SELECT model, speed, hd from PC WHERE price < :price",
                Collections.singletonMap("price", priceLessThan),
                new BeanPropertyRowMapper<>(ComputerTemplateEntity.class)
        );
    }

}