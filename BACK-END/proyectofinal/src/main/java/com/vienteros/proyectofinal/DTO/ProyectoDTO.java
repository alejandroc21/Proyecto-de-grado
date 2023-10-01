package com.vienteros.proyectofinal.DTO;


import com.vienteros.proyectofinal.model.Planeacion;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProyectoDTO {
    private int idUsuario;
    private int id;
    private String nombre;
    private int diasEstimados;
    private int idPlaneacion;
}
