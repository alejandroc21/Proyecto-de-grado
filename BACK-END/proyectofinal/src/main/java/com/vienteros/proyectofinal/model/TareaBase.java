package com.vienteros.proyectofinal.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tarea_base")
public class TareaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tarea;
    private boolean realizado;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
