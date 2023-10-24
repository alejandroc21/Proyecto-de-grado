package com.vienteros.proyectofinal.DTO;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsumoDTO {

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    private Date fecha;
}
