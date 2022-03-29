package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;

import java.util.List;

public interface ComputerRepository {

    List<ComputerEntity> findAllBy(ComputerFilter computerFilter);

    PersistenceType getType();

}