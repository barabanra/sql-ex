package ru.barabanra.sqlex.persistence.repository;

import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerTemplateRepository {

    List<ComputerTemplateEntity> findAllByPriceLessThanEqual(BigDecimal priceLessThan);

}