package ru.barabanra.sqlex.persistence.repository.specification;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.barabanra.sqlex.dto.request.ComputerFilter;
import ru.barabanra.sqlex.persistence.entity.jpa.ComputerJpaEntity;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ComputerJpaEntitySpecification {

    public static Specification<ComputerJpaEntity> getFilter(ComputerFilter params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getPriceLessThan() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("price"), params.getPriceLessThan()));
            }
            if (CollectionUtils.isNotEmpty(params.getCdTypeList())) {
                predicates.add(root.get("cd").in(params.getCdTypeList()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}