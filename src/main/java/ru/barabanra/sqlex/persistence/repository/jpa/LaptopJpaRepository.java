package ru.barabanra.sqlex.persistence.repository.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.mapper.LaptopMapper;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.LaptopJpaEntity;
import ru.barabanra.sqlex.persistence.repository.LaptopRepository;
import ru.barabanra.sqlex.persistence.repository.specification.LaptopJpaEntitySpecification;

import java.util.List;

public interface LaptopJpaRepository extends JpaRepository<LaptopJpaEntity, String>,
        LaptopRepository,
        JpaSpecificationExecutor<LaptopJpaEntity> {

    LaptopMapper mapper = Mappers.getMapper(LaptopMapper.class);

    @Override
    default List<LaptopEntity> findBy(LaptopFilter filter) {
        Specification<LaptopJpaEntity> spec = LaptopJpaEntitySpecification.getFilter(filter);
        List<LaptopJpaEntity> all = findAll(spec);
        return mapper.mapFromJpa(all);
    }

    @Override
    default PersistenceType getType() {
        return PersistenceType.JPA;
    }

}