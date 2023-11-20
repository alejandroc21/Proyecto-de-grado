package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.TareaDTO;
import com.vienteros.proyectofinal.model.Tarea;

import java.util.List;

public interface ITareaService {
    List<TareaDTO> listarTareas(int idProyecto);

    TareaDTO crearTarea(Tarea tarea);

    TareaDTO actualizarTarea(Tarea tarea);

    String eliminarTarea(int id);
}
