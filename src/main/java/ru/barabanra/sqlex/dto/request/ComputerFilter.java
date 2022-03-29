package ru.barabanra.sqlex.dto.request;

import lombok.Data;
import ru.barabanra.sqlex.dto.properties.PersistenceType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ComputerFilter {

    private BigDecimal priceLessThan;

    @NotNull
    private PersistenceType persistenceType;

    private List<String> cdTypeList;

}