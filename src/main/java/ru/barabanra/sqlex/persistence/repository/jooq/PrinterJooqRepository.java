package ru.barabanra.sqlex.persistence.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.PrinterMapper;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;
import ru.barabanra.sqlex.persistence.repository.PrinterRepository;
import ru.barabanra.sqlex.tables.Printer;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrinterJooqRepository implements PrinterRepository {

    private final DSLContext dslContext;
    private final PrinterMapper printerMapper;

    @Override
    public List<PrinterEntity> findALL() {
        return dslContext.selectFrom(Printer.PRINTER)
                .fetchInto(Printer.PRINTER)
                .stream()
                .map(printerMapper::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JOOQ;
    }

}