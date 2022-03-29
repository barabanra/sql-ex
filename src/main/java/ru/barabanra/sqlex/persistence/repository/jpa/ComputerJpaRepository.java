package ru.barabanra.sqlex.persistence.repository.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.persistence.entity.ComputerEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;
import ru.barabanra.sqlex.persistence.repository.ComputerRepository;
import ru.barabanra.sqlex.persistence.repository.specification.ComputerJpaEntitySpecification;

import java.util.List;

public interface ComputerJpaRepository extends JpaRepository<ComputerJpaEntity, Long>,
        ComputerRepository,
        JpaSpecificationExecutor<ComputerJpaEntity> {

    ComputerMapper computerMapper = Mappers.getMapper(ComputerMapper.class);

    @Override
    default List<ComputerEntity> findAllBy(ComputerFilter filter) {
        Specification<ComputerJpaEntity> spec = ComputerJpaEntitySpecification.getFilter(filter);
        List<ComputerJpaEntity> all = findAll(spec);
        return computerMapper.mapFromJpa(all);
    }

    @Override
    default PersistenceType getType() {
        return PersistenceType.JPA;
    }

}