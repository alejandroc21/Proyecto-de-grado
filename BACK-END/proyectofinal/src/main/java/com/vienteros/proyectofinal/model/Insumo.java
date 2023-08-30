package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;

    @ManyToOne(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;
}
