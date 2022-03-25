package ru.barabanra.sqlex.mapper;

import org.mapstruct.Mapper;
import ru.barabanra.sqlex.dto.response.PrinterResponseDto;
import ru.barabanra.sqlex.dto.service.PrinterDto;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.PrinterJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.PrinterTemplateEntity;
import ru.barabanra.sqlex.tables.records.PrinterRecord;

import java.util.List;

@Mapper
public abstract class PrinterMapper {

    public abstract PrinterResponseDto map(PrinterDto source);

    public abstract List<PrinterResponseDto> map(List<PrinterDto> source);

    public abstract List<PrinterDto> mapDto(List<PrinterEntity> source);

    public abstract PrinterDto mapDto(PrinterEntity source);

    public abstract PrinterEntity mapEntity(PrinterTemplateEntity source);

    public abstract PrinterEntity mapEntity(PrinterRecord source);

    public abstract List<PrinterEntity> mapEntity(List<PrinterJpaEntity> source);

    public abstract PrinterEntity mapEntity(PrinterJpaEntity source);

}