package com.vienteros.proyectofinal.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {

    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private Date fecha;
    private int FacturaId;
}
