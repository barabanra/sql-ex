package ru.barabanra.sqlex.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.service.ComputerDto;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;
import ru.barabanra.sqlex.persistence.entity.template.ComputerTemplateEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerJooqRepository;
import ru.barabanra.sqlex.persistence.repository.ComputerJpaRepository;
import ru.barabanra.sqlex.persistence.repository.ComputerTemplateRepository;
import ru.barabanra.sqlex.service.ComputerService;
import ru.barabanra.sqlex.tables.records.PcRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

    private final ComputerMapper computerMapper;
    private final ComputerTemplateRepository computerTemplateRepository;
    private final ComputerJpaRepository computerJpaRepository;
    private final ComputerJooqRepository computerJooqRepository;

    @Override
    public List<ComputerDto> findByPriceLessThan(PersistenceType persistenceType,
                                                 BigDecimal priceLessThan) {
        if (persistenceType.isTemplate()) {
            List<ComputerTemplateEntity> byPriceLessThan = computerTemplateRepository.findAllByPriceLessThanEqual(priceLessThan);
            return computerMapper.mapTemplate(byPriceLessThan);
        }
        if (persistenceType.isJpa()) {
            List<ComputerJpaEntity> byPriceLessThan = computerJpaRepository.findAllByPriceLessThanEqual(priceLessThan);
            return computerMapper.mapJpa(byPriceLessThan);
        }
        if (persistenceType.isJooq()) {
            List<PcRecord> byPriceLessThan = computerJooqRepository.findAllByPriceLessThanEqual(priceLessThan);
            return computerMapper.mapJooq(byPriceLessThan);
        }
        return new ArrayList<>();
    }

}