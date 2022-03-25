package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;

import java.util.List;

public interface PrinterRepository {

    PersistenceType getType();

    List<PrinterEntity> findALL();

}