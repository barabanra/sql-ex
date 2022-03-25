package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerRepository {

    List<ComputerEntity> findAllBy(BigDecimal priceLessThan);

    PersistenceType getType();

}