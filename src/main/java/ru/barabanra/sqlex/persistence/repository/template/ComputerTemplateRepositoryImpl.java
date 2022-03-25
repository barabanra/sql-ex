package ru.barabanra.sqlex.persistence.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ComputerTemplateRepositoryImpl implements ComputerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ComputerMapper computerMapper;

    @Override
    public List<ComputerEntity> findAllBy(BigDecimal priceLessThan) {
        return jdbcTemplate.query(
                "SELECT model, speed, hd from PC WHERE price < :price",
                Collections.singletonMap("price", priceLessThan),
                new BeanPropertyRowMapper<>(ComputerTemplateEntity.class))
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TEMPLATE;
    }

}