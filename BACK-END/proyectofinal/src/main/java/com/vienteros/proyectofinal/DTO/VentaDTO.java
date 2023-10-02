package com.vienteros.proyectofinal.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class VentaDTO {
    private int idProyecto;
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private Date fecha;
}
