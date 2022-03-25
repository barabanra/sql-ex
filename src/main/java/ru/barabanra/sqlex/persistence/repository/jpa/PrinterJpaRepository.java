package ru.barabanra.sqlex.persistence.repository.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.mapper.PrinterMapper;
import ru.barabanra.sqlex.persistence.entity.PrinterEntity;
import ru.barabanra.sqlex.persistence.entity.jpa.PrinterJpaEntity;
import ru.barabanra.sqlex.persistence.repository.PrinterRepository;

import java.util.List;

public interface PrinterJpaRepository extends JpaRepository<PrinterJpaEntity, Integer>, PrinterRepository {

    PrinterMapper computerMapper = Mappers.getMapper(PrinterMapper.class);

    @Override
    default List<PrinterEntity> findALL() {
        List<PrinterJpaEntity> all = findAll();
        return computerMapper.mapEntity(all);
    }

    @Override
    default PersistenceType getType() {
        return PersistenceType.JPA;
    }

}