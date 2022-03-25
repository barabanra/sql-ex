package ru.barabanra.sqlex.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrinterResponseDto {

    private Integer code;

    private String model;

    private String color;

    private String type;

    private BigDecimal price;

}