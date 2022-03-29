package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;

import java.util.List;

public interface LaptopRepository {

    PersistenceType getType();

    List<LaptopEntity> findBy(LaptopFilter laptopFilter);

}