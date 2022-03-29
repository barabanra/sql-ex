package ru.barabanra.sqlex.persistence.entity.template;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerTemplateEntity {

    private String model;

    private Integer speed;

    private Integer hd;

    private BigDecimal price;

    private String cd;

}