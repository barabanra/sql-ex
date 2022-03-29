package ru.barabanra.sqlex.dto.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerDto {

    private String model;

    private Integer speed;

    private Integer hd;

    private BigDecimal price;

    private String cd;

}