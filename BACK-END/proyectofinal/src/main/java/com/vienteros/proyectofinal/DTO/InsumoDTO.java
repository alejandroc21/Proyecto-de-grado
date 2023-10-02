package com.vienteros.proyectofinal.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InsumoDTO {
    private int idProyecto;
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
}
