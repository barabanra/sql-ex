package ru.barabanra.sqlex.service.impl;

import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.service.PrinterDto;
import ru.barabanra.sqlex.mapper.PrinterMapper;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;
import ru.barabanra.sqlex.persistence.repository.PrinterRepository;
import ru.barabanra.sqlex.service.PrinterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PrinterServiceImpl implements PrinterService {

    private final PrinterMapper printerMapper;
    private final Map<PersistenceType, PrinterRepository> printerRepositoryMap;

    public PrinterServiceImpl(PrinterMapper printerMapper,
                              List<PrinterRepository> printerRepositories) {
        this.printerMapper = printerMapper;
        this.printerRepositoryMap = buildPrinterRepositoryMap(printerRepositories);
    }

    @Override
    public List<PrinterDto> findAll(PersistenceType persistenceType) {
        PrinterRepository printerRepository = printerRepositoryMap.get(persistenceType);
        if (printerRepository == null) {
            return new ArrayList<>();
        }
        List<PrinterEntity> all = printerRepository.findALL();
        return printerMapper.mapDto(all);
    }

    private Map<PersistenceType, PrinterRepository> buildPrinterRepositoryMap(List<PrinterRepository> printerRepositories) {
        return printerRepositories
                .stream()
                .collect(Collectors.toMap(PrinterRepository::getType, Function.identity()));
    }

}