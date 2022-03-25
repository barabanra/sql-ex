package ru.barabanra.sqlex.dto.request;

import lombok.Data;
import ru.barabanra.sqlex.dto.properties.PersistenceType;

@Data
public class ProductFilter {

    private PersistenceType persistenceType;

    private String type;

}