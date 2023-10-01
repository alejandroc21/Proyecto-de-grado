package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.PlaneacionDTO;
import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Usuario;

import java.util.List;

public interface IProyectoService {
    Proyecto getForId(int id);

    List<Proyecto> getProyectos(int idUsuario);


    Proyecto crearProyecto(ProyectoDTO proyectoDTO);

    Proyecto actulizarProyecto(ProyectoDTO proyectoDTO);

    String eliminarProyecto(ProyectoDTO proyectoDTO);
}
