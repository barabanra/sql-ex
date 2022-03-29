package ru.barabanra.sqlex.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerResponseDto {

    private String model;

    private Integer speed;

    private Integer hd;

    private BigDecimal price;

    private String cd;

}