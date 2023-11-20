package com.vienteros.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToMany(targetEntity = TareaBase.class, fetch = FetchType.LAZY, mappedBy = "categoria")
    @JsonIgnore
    private List<TareaBase> tareasBase;

    @OneToMany(targetEntity = Proyecto.class, fetch = FetchType.LAZY, mappedBy = "categoria")
    @JsonIgnore
    private List<Proyecto> proyectos;

}
