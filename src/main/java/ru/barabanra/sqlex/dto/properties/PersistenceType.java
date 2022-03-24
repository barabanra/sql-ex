package ru.barabanra.sqlex.dto.properties;

public enum PersistenceType {

    TEMPLATE,
    JPA,
    JOOQ;

    public boolean isTemplate() {
        return PersistenceType.TEMPLATE.equals(this);
    }

    public boolean isJpa() {
        return PersistenceType.JPA.equals(this);
    }

    public boolean isJooq() {
        return PersistenceType.JOOQ.equals(this);
    }

}