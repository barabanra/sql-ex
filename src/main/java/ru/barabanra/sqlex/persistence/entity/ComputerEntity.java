package ru.barabanra.sqlex.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerEntity {

    private String model;

    private Integer speed;

    private Integer hd;

    private BigDecimal price;

    private String cd;

}