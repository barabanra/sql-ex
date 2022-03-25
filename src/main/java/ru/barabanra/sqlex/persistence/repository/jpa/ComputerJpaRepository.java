package ru.barabanra.sqlex.persistence.repository.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerJpaRepository extends JpaRepository<ComputerJpaEntity, Long>, ComputerRepository {

    ComputerMapper computerMapper = Mappers.getMapper(ComputerMapper.class);

    List<ComputerJpaEntity> findAllByPriceLessThanEqual(BigDecimal priceLessThan);

    @Override
    default List<ComputerEntity> findAllBy(BigDecimal priceLessThan) {
        List<ComputerJpaEntity> all = findAllByPriceLessThanEqual(priceLessThan);
        return computerMapper.mapFromJpa(all);
    }

    @Override
    default PersistenceType getType() {
        return PersistenceType.JPA;
    }

}