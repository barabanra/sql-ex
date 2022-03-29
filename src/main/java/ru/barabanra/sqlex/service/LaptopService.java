package ru.barabanra.sqlex.service;

import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.dto.service.LaptopDto;

import java.util.List;

public interface LaptopService {

    List<LaptopDto> findBy(LaptopFilter laptopFilter);

}