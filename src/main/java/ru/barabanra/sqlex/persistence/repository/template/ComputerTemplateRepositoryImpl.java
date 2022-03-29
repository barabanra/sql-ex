package ru.barabanra.sqlex.persistence.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;
import ru.barabanra.sqlex.utils.SqlFilters;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ComputerTemplateRepositoryImpl implements ComputerRepository {

    public static final String SIMPLE_SELECT_PC = """
            SELECT model, speed, hd, price, cd from PC
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ComputerMapper computerMapper;

    @Override
    public List<ComputerEntity> findAllBy(ComputerFilter computerFilter) {
        SqlFilters sqlFilters = SqlFilters.builder()
                .in("CD", computerFilter.getCdTypeList())
                .lte("PRICE", computerFilter.getPriceLessThan())
                .build();

        String query = SIMPLE_SELECT_PC;

        if (!sqlFilters.isEmpty()) {
            query = query.concat(" WHERE " + sqlFilters.getPredicate());
        }
        return jdbcTemplate.query(query, sqlFilters.getParams(),
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