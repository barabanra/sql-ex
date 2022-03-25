package ru.barabanra.sqlex.persistence.entity.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PRODUCT")
public class ProductJpaEntity {

    @Id
    private String model;

    private String maker;

    private String type;

}