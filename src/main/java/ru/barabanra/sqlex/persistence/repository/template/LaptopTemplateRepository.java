package ru.barabanra.sqlex.persistence.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.mapper.LaptopMapper;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;
import ru.barabanra.sqlex.persistence.entity.template.LaptopTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.LaptopRepository;
import ru.barabanra.sqlex.utils.SqlFilters;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LaptopTemplateRepository implements LaptopRepository {

    private static final String SELECT_LAPTOP = """
            SELECT code, PRODUCT.model as model, LAPTOP.model as laptopModel, speed, ram, hd, price, screen, maker, type
             FROM PRODUCT INNER JOIN LAPTOP ON LAPTOP.model = PRODUCT.model
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LaptopMapper laptopMapper;

    @Override
    public List<LaptopEntity> findBy(LaptopFilter laptopFilter) {
        SqlFilters sqlFilters = SqlFilters.builder()
                .gt("HD", laptopFilter.getHdMoreThan())
                .build();

        String query = SELECT_LAPTOP;

        if (!sqlFilters.isEmpty()) {
            query = query.concat(" WHERE " + sqlFilters.getPredicate());
        }

        return jdbcTemplate.query(query, sqlFilters.getParams(),
                new BeanPropertyRowMapper<>(LaptopTemplateEntity.class))
                .stream()
                .map(laptopMapper::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TEMPLATE;
    }

}