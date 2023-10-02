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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int id;
    private String nombre;
    @Column(name = "tiempo_estimado_dd")
    private int diasEstimados;

    @OneToMany(targetEntity = Insumo.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    @JsonIgnore
    private List<Insumo> insumos;

    @OneToMany(targetEntity = Producto.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    @JsonIgnore
    private List<Producto> productos;


    @OneToMany(targetEntity = Venta.class, fetch = FetchType.LAZY, mappedBy = "proyecto")
    @JsonIgnore
    private List<Venta> ventas;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;
}
