package ru.barabanra.sqlex.dto.properties;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class RequestResponseFilterProperties {

    private Integer maxPayLoad = 100000;

    private List<String> endpoint = Collections.singletonList("/api/*");

}