package com.vienteros.proyectofinal.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Planeacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planeacion")
    private int id;
    private double inversion;
    private double gasto;
    private double total;

    @ManyToOne(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;
}
