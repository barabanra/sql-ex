package ru.barabanra.sqlex.persistence.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;
import ru.barabanra.sqlex.tables.Pc;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerJooqRepositoryImpl implements ComputerRepository {

    private final DSLContext dslContext;
    private final ComputerMapper computerMapper;

    @Override
    public List<ComputerEntity> findAllBy(BigDecimal priceLessThan) {
        return dslContext.selectFrom(Pc.PC)
                .where(Pc.PC.PRICE.lessOrEqual(priceLessThan))
                .fetchInto(Pc.PC)
                .stream()
                .map(computerMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JOOQ;
    }

}