package ru.barabanra.sqlex.service;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.service.ComputerDto;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerService {

    List<ComputerDto> findByPriceLessThan(PersistenceType persistenceType,
                                          BigDecimal priceLessThan);

}