package ru.barabanra.sqlex.persistence.entity.template;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrinterTemplateEntity {

    private Integer code;

    private String model;

    private String color;

    private String type;

    private BigDecimal price;

}