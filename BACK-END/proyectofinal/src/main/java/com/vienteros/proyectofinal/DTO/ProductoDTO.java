package com.vienteros.proyectofinal.DTO;

import lombok.*;


import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private int cantidadInicial;
    private int cantidadFinal;
    private double precio;
    private Date fecha;
}
