package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;
    private String nombre;
    private String email;
    private String password;

    @OneToMany(targetEntity = AsignarProyecto.class, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<AsignarProyecto> asignarProyectos;
}
