package com.vienteros.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private double precio;
    //@Column(columnDefinition = "DATE")
    private Date fecha;

    @ManyToOne(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;


}
