package ru.barabanra.sqlex.persistence.entity.jpa;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "LAPTOP")
public class LaptopJpaEntity {

    @Id
    private Long code;

    private Long speed;

    private Long ram;

    private Long hd;

    private BigDecimal price;

    private Long screen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model")
    private ProductJpaEntity product;

}