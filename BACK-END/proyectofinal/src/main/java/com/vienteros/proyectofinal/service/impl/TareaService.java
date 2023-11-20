package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.TareaDTO;
import com.vienteros.proyectofinal.model.Tarea;
import com.vienteros.proyectofinal.repository.TareaRepository;
import com.vienteros.proyectofinal.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository repository;

    @Override
    public List<TareaDTO> listarTareas(int idProyecto) {
        List<Tarea> tareas = repository.findByProyectoId(idProyecto);
        List<TareaDTO> tareasDTO = tareas.stream().map(tarea -> {
            TareaDTO tareaDTO = TareaDTO.builder()
                    .id(tarea.getId())
                    .tarea(tarea.getTarea())
                    .realizado(tarea.isRealizado())
                    .build();
            return tareaDTO;
        }).collect(Collectors.toList());
        return tareasDTO;
    }

    @Override
    public TareaDTO crearTarea(Tarea tarea) {
        Tarea tarea1 = repository.save(tarea);
        TareaDTO tareaDTO = TareaDTO.builder()
                .id(tarea1.getId())
                .tarea(tarea1.getTarea())
                .realizado(tarea1.isRealizado())
                .build();
        return tareaDTO;
    }

    @Override
    public TareaDTO actualizarTarea(Tarea tarea) {
        Tarea tarea1 = repository.save(tarea);
        TareaDTO tareaDTO = TareaDTO.builder()
                .id(tarea1.getId())
                .tarea(tarea1.getTarea())
                .realizado(tarea1.isRealizado())
                .build();
        return tareaDTO;
    }

    @Override
    public String eliminarTarea(int id) {
        repository.deleteById(id);
        return "tarea eliminada";
    }
}
