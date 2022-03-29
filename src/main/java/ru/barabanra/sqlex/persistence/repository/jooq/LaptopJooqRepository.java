package ru.barabanra.sqlex.persistence.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.mapper.LaptopMapper;
import ru.barabanra.sqlex.persistence.entity.LaptopEntity;
import ru.barabanra.sqlex.persistence.repository.LaptopRepository;
import ru.barabanra.sqlex.tables.Laptop;
import ru.barabanra.sqlex.tables.Product;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LaptopJooqRepository implements LaptopRepository {

    private final LaptopMapper laptopMapper;
    private final DSLContext dslContext;

    @Override
    public List<LaptopEntity> findBy(LaptopFilter laptopFilter) {
        Condition condition = DSL.noCondition();

        if (laptopFilter.getHdMoreThan() != null) {
            condition = condition.and(Laptop.LAPTOP.HD.greaterThan(laptopFilter.getHdMoreThan().floatValue()));
        }
        return dslContext.select()
                .from(Laptop.LAPTOP.innerJoin(Product.PRODUCT)
                        .on(Laptop.LAPTOP.MODEL.eq(Product.PRODUCT.MODEL)))
                .where(condition)
                .fetchGroups(Laptop.LAPTOP, Product.PRODUCT)
                .entrySet()
                .stream()
                .map(laptopMapper::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JOOQ;
    }

}