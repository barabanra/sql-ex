package ru.barabanra.sqlex.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrinterEntity {

    private Integer code;

    private String model;

    private String color;

    private String type;

    private BigDecimal price;

}