package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "asignar_proyectos")
public class AsignarProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignar")
    private int id;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;
}
