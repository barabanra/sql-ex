package ru.barabanra.sqlex.persistence.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.PrinterMapper;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;
import ru.barabanra.sqlex.persistence.entity.template.PrinterTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.PrinterRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PrinterTemplateRepository implements PrinterRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PrinterMapper printerMapper;

    @Override
    public List<PrinterEntity> findALL() {
        return jdbcTemplate.query(
                "SELECT code, model, color, type, price from PRINTER",
                Collections.emptyMap(),
                new BeanPropertyRowMapper<>(PrinterTemplateEntity.class))
                .stream()
                .map(printerMapper::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TEMPLATE;
    }

}