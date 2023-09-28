package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int id;
    private String tipo;
    @Column(name = "tiempo_estimado_dd")
    private int tiempoEstimadoDias;

    @OneToMany(targetEntity = Insumo.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    private List<Insumo> insumos;

    @OneToMany(targetEntity = Planeacion.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    private List<Planeacion> planeaciones;

    @OneToMany(targetEntity = Producto.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    private List<Producto> productos;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
