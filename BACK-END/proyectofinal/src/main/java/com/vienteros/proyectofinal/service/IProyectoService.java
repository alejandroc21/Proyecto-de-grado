package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.TareaBase;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProyectoService {

    List<ProyectoDTO> getProyectos(int idUsuario);

    ProyectoDTO guardarProyecto(Proyecto proyecto);

    ProyectoDTO actulizarProyecto(Proyecto proyecto);

    String eliminarProyecto(int id);

    Iterable<TareaBase> getTareaBase(int idProyecto);
}
