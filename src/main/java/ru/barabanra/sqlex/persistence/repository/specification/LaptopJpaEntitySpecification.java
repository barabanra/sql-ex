package ru.barabanra.sqlex.persistence.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.persistence.entity.jpa.LaptopJpaEntity;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LaptopJpaEntitySpecification {

    public static Specification<LaptopJpaEntity> getFilter(LaptopFilter params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getHdMoreThan() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("hd"), params.getHdMoreThan()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}