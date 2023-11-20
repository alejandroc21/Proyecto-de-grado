package com.vienteros.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String nombre;
    private String descripcion;

    @OneToMany(targetEntity = Insumo.class, cascade = CascadeType.REMOVE, mappedBy = "proyecto")
    @JsonIgnore
    private List<Insumo> insumos;

    @OneToMany(targetEntity = Producto.class, cascade = CascadeType.REMOVE, mappedBy = "proyecto")
    @JsonIgnore
    private List<Producto> productos;

    @OneToMany(targetEntity = Venta.class, cascade = CascadeType.REMOVE, mappedBy = "proyecto")
    @JsonIgnore
    private List<Venta> ventas;

    @OneToMany(targetEntity = Tarea.class, cascade = CascadeType.REMOVE, mappedBy = "proyecto")
    @JsonIgnore
    private List<Tarea> tareas;

    @OneToMany(targetEntity = Cliente.class, cascade = CascadeType.REMOVE, mappedBy = "proyecto")
    @JsonIgnore
    private List<Cliente> clientes;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
