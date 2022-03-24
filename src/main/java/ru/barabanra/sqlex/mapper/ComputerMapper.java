package ru.barabanra.sqlex.mapper;

import org.mapstruct.Mapper;
import ru.barabanra.sqlex.dto.response.ComputerResponseDto;
import ru.barabanra.sqlex.dto.service.ComputerDto;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.tables.records.PcRecord;

import java.util.List;

@Mapper
public abstract class ComputerMapper {

    public abstract List<ComputerDto> mapTemplate(List<ComputerTemplateEntity> source);

    public abstract ComputerDto mapTemplate(ComputerTemplateEntity source);

    public abstract List<ComputerResponseDto> mapResponse(List<ComputerDto> byPriceLessThan);

    public abstract ComputerResponseDto mapResponse(ComputerDto byPriceLessThan);

    public abstract List<ComputerDto> mapJpa(List<ComputerJpaEntity> source);

    public abstract ComputerDto mapJpa(ComputerJpaEntity source);

    public abstract ComputerDto mapJooq(PcRecord source);

    public abstract List<ComputerDto> mapJooq(List<PcRecord> source);

}