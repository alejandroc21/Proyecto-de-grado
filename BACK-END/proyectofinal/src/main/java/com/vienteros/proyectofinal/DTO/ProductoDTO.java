package com.vienteros.proyectofinal.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;

@Getter @Setter
public class ProductoDTO {
    private int idProyecto;
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidadInicial;
    private int cantidadFinal;
    private double precio;
    private Date fecha;
}
