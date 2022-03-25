package ru.barabanra.sqlex.dto.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrinterDto {

    private Integer code;

    private String model;

    private String color;

    private String type;

    private BigDecimal price;

}