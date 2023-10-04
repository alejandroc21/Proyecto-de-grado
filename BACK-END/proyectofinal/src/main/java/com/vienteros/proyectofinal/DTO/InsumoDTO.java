package com.vienteros.proyectofinal.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsumoDTO {

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
}
