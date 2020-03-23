package com.api.practica.security;

import com.api.practica.commons.entity.BaseEntity;
import com.api.practica.commons.entity.BaseEntityWithTimeStamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol extends BaseEntityWithTimeStamp {
    private String nombre;
    private String description;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}