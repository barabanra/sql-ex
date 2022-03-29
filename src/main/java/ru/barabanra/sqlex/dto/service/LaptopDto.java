package ru.barabanra.sqlex.dto.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LaptopDto {

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