package com.vienteros.proyectofinal.DTO;


import com.vienteros.proyectofinal.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProyectoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
}
