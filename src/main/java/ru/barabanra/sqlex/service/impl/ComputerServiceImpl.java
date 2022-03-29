package ru.barabanra.sqlex.service.impl;

import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.dto.service.ComputerDto;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;
import ru.barabanra.sqlex.service.ComputerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ComputerServiceImpl implements ComputerService {

    private final ComputerMapper computerMapper;
    private final Map<PersistenceType, ComputerRepository> computerRepositoryMap;

    public ComputerServiceImpl(ComputerMapper computerMapper,
                               List<ComputerRepository> computerRepositories) {
        this.computerMapper = computerMapper;
        this.computerRepositoryMap = buildComputerMap(computerRepositories);
    }

    @Override
    public List<ComputerDto> findBy(ComputerFilter computerFilter) {
        ComputerRepository computerRepository = computerRepositoryMap.get(computerFilter.getPersistenceType());
        if (computerRepository == null) {
            return new ArrayList<>();
        }
        List<ComputerEntity> allBy = computerRepository.findAllBy(computerFilter);
        return computerMapper.map(allBy);
    }

    private Map<PersistenceType, ComputerRepository> buildComputerMap(List<ComputerRepository> computerRepositories) {
        return computerRepositories
                .stream()
                .collect(Collectors.toMap(ComputerRepository::getType, Function.identity()));
    }

}