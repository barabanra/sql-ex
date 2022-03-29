package ru.barabanra.sqlex.dto.request;

import lombok.Data;
import ru.barabanra.sqlex.dto.properties.PersistenceType;

import javax.validation.constraints.NotNull;

@Data
public class LaptopFilter {

    @NotNull
    private PersistenceType persistenceType;

    private Long hdMoreThan;

}