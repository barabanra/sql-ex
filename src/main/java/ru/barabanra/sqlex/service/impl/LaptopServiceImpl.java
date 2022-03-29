package ru.barabanra.sqlex.service.impl;

import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.dto.service.LaptopDto;
import ru.barabanra.sqlex.mapper.LaptopMapper;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;
import ru.barabanra.sqlex.persistence.repository.LaptopRepository;
import ru.barabanra.sqlex.service.LaptopService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopMapper laptopMapper;
    private final Map<PersistenceType, LaptopRepository> laptopRepositoryMap;

    public LaptopServiceImpl(LaptopMapper laptopMapper,
                             List<LaptopRepository> laptopRepositoryMap) {
        this.laptopMapper = laptopMapper;
        this.laptopRepositoryMap = buildRepositoryMap(laptopRepositoryMap);
    }

    @Override
    public List<LaptopDto> findBy(LaptopFilter laptopFilter) {
        LaptopRepository repository = laptopRepositoryMap.get(laptopFilter.getPersistenceType());
        List<LaptopEntity> allBy = repository.findBy(laptopFilter);
        return laptopMapper.mapService(allBy);
    }

    private Map<PersistenceType, LaptopRepository> buildRepositoryMap(List<LaptopRepository> laptopRepositoryMap) {
        return laptopRepositoryMap
                .stream()
                .collect(Collectors.toMap(LaptopRepository::getType, Function.identity()));
    }

}