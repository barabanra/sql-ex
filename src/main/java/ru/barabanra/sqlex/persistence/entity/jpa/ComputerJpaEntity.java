package ru.barabanra.sqlex.persistence.entity.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PC")
public class ComputerJpaEntity {

    @Id
    private Long code;

    private String model;

    private Integer speed;

    private Integer hd;

    private BigDecimal price;

}