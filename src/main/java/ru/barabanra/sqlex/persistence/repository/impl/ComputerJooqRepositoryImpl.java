package ru.barabanra.sqlex.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.persistence.repository.ComputerJooqRepository;
import ru.barabanra.sqlex.tables.Pc;
import ru.barabanra.sqlex.tables.records.PcRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerJooqRepositoryImpl implements ComputerJooqRepository {

    private final DSLContext dslContext;

    @Override
    public List<PcRecord> findAllByPriceLessThanEqual(BigDecimal priceLessThan) {
         return dslContext.selectFrom(Pc.PC)
                .where(Pc.PC.PRICE.lessOrEqual(priceLessThan))
                .fetchInto(Pc.PC)
                .collect(Collectors.toList());
    }

}