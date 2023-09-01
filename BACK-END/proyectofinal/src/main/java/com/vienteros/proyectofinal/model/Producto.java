package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id;
    private String nombre;
    private String descripcion;
    @Column(name = "cantidad_inicial")
    private int cantidadInicial;
    @Column(name = "cantidad_final")
    private int cantidadFinal;
    private double peso;
    //@Column(columnDefinition = "DATE")
    private Date fecha;

    @ManyToOne(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;
}
