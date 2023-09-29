package com.vienteros.proyectofinal.DTO;

import com.vienteros.proyectofinal.model.Proyecto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String email;
}
