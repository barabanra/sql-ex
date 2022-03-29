package ru.barabanra.sqlex.service;

import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.dto.service.ComputerDto;

import java.util.List;

public interface ComputerService {

    List<ComputerDto> findBy(ComputerFilter computerFilter);

}