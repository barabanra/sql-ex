package ru.barabanra.sqlex.persistence.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;
import ru.barabanra.sqlex.tables.Pc;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerJooqRepositoryImpl implements ComputerRepository {

    private final DSLContext dslContext;
    private final ComputerMapper computerMapper;

    @Override
    public List<ComputerEntity> findAllBy(ComputerFilter computerFilter) {
        Condition condition = DSL.noCondition();

        if (computerFilter.getPriceLessThan() != null) {
            condition = condition.and(Pc.PC.PRICE.lessThan(computerFilter.getPriceLessThan()));
        }
        if (CollectionUtils.isNotEmpty(computerFilter.getCdTypeList())) {
            condition = condition.and(Pc.PC.CD.in(computerFilter.getCdTypeList()));
        }
        return dslContext.selectFrom(Pc.PC)
                .where(condition)
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