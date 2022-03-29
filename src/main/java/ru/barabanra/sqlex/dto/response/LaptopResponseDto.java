package ru.barabanra.sqlex.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LaptopResponseDto {

    private Long code;

    private Long model;

    private Long speed;

    private Long ram;

    private Long hd;

    private BigDecimal price;

    private Long screen;

    private String maker;

    private String type;

}