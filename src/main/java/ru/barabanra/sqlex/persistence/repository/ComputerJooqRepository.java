package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.tables.records.PcRecord;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerJooqRepository {

    List<PcRecord> findAllByPriceLessThanEqual(BigDecimal priceLessThan);

}