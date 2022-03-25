package ru.barabanra.sqlex.service;

import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.service.PrinterDto;

import java.util.List;

public interface PrinterService {

    List<PrinterDto> findAll(PersistenceType persistenceType);

}