package ru.barabanra.sqlex.mapper;

import org.jooq.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.barabanra.sqlex.dto.response.LaptopResponseDto;
import ru.barabanra.sqlex.dto.service.LaptopDto;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.LaptopJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.LaptopTemplateEntity;
import ru.barabanra.sqlex.tables.records.LaptopRecord;
import ru.barabanra.sqlex.tables.records.ProductRecord;

import java.util.List;
import java.util.Map;

@Mapper
public abstract class LaptopMapper {

    public abstract List<LaptopResponseDto> mapResponse(List<LaptopDto> source);

    public abstract LaptopResponseDto mapResponse(LaptopDto source);

    public abstract List<LaptopDto> mapService(List<LaptopEntity> source);

    public abstract LaptopDto mapService(LaptopEntity source);

    public abstract LaptopEntity mapEntity(LaptopTemplateEntity source);

    @Mapping(target = "model", source = "laptopSource.model")
    public abstract LaptopEntity mapEntity(LaptopRecord laptopSource, ProductRecord productSource);

    public LaptopEntity mapEntity(Map.Entry<LaptopRecord, Result<ProductRecord>> source) {
        ProductRecord productRecord = source.getValue()
                .stream()
                .findFirst()
                .orElse(null);

        return mapEntity(source.getKey(), productRecord);
    }

    public abstract List<LaptopEntity> mapFromJpa(List<LaptopJpaEntity> source);


    @Mapping(target = "type", source = "source.product.type")
    @Mapping(target = "maker", source = "source.product.maker")
    @Mapping(target = "model", source = "source.product.model")
    public abstract LaptopEntity mapFromJpa(LaptopJpaEntity source);

}