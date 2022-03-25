package ru.barabanra.sqlex.mapper;

import org.mapstruct.Mapper;
import ru.barabanra.sqlex.dto.response.ProductResponseDto;
import ru.barabanra.sqlex.dto.service.ProductDto;
import ru.barabanra.sqlex.persistence.entity.ProductEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.ProductJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.ProductTemplateEntity;
import ru.barabanra.sqlex.tables.records.ProductRecord;

import java.util.List;

@Mapper
public abstract class ProductMapper {

    public abstract List<ProductResponseDto> map(List<ProductDto> source);

    public abstract ProductResponseDto map(ProductDto source);

    public abstract List<ProductDto> mapDto(List<ProductEntity> source);

    public abstract ProductDto mapDto(ProductEntity source);

    public abstract List<ProductEntity> mapEntity(List<ProductJpaEntity> source);

    public abstract ProductEntity mapEntity(ProductJpaEntity source);

    public abstract ProductEntity mapEntityFromJooq(ProductRecord source);

    public abstract ProductEntity map(ProductTemplateEntity source);

}