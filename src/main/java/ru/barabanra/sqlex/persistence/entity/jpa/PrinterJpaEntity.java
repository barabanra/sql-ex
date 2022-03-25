package ru.barabanra.sqlex.persistence.entity.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PRINTER")
public class PrinterJpaEntity {

    @Id
    private Integer code;

    private String model;

    private String color;

    private String type;

    private BigDecimal price;

}