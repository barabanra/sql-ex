package ru.barabanra.sqlex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ComputerJpaRepository extends JpaRepository<ComputerJpaEntity, Long> {

    List<ComputerJpaEntity> findAllByPriceLessThanEqual(BigDecimal priceLessThan);

}