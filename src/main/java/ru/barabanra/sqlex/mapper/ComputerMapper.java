package ru.barabanra.sqlex.mapper;

import org.mapstruct.Mapper;
import ru.barabanra.sqlex.dto.response.ComputerResponseDto;
import ru.barabanra.sqlex.dto.service.ComputerDto;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.tables.records.PcRecord;

import java.util.List;

@Mapper
public abstract class ComputerMapper {

    public abstract List<ComputerResponseDto> mapResponse(List<ComputerDto> byPriceLessThan);

    public abstract ComputerResponseDto mapResponse(ComputerDto byPriceLessThan);

    public abstract ComputerEntity map(PcRecord source);

    public abstract ComputerEntity map(ComputerTemplateEntity source);

    public abstract List<ComputerEntity> mapFromJpa(List<ComputerJpaEntity> source);

    public abstract List<ComputerDto> map(List<ComputerEntity> allBy);
}