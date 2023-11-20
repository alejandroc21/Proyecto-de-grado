package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Tarea;
import com.vienteros.proyectofinal.model.TareaBase;
import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.repository.ProyectoRepository;
import com.vienteros.proyectofinal.repository.TareaBaseRepository;
import com.vienteros.proyectofinal.repository.TareaRepository;
import com.vienteros.proyectofinal.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private ProyectoRepository repository;

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<ProyectoDTO> getProyectos(int idUsuario) {
        List<Proyecto> proyectos = repository.findByUsuarioId(idUsuario);

        List<ProyectoDTO> proyectosDTO = proyectos.stream().map(proyecto -> {
            ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                    .id(proyecto.getId())
                    .nombre(proyecto.getNombre())
                    .descripcion(proyecto.getDescripcion())
                    .categoria(proyecto.getCategoria())
                    .build();
            return proyectoDTO;
        }).collect(Collectors.toList());

        return  proyectosDTO;
    }

    @Override
    public ProyectoDTO guardarProyecto(Proyecto proyecto) {
        Proyecto project = repository.save(proyecto);

        List<TareaBase> tareasBase = repository.obtenerTareasPorProyecto(proyecto.getId());
        List<Tarea> tareas = tareasBase.stream().map(tareaBase -> {
            Tarea tarea = Tarea.builder()
                    .tarea(tareaBase.getTarea())
                    .realizado(tareaBase.isRealizado())
                    .proyecto(project)
                    .build();
            return tarea;
        }).collect(Collectors.toList());
        tareaRepository.saveAll(tareas);

        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .id(project.getId())
                .nombre(project.getNombre())
                .descripcion(proyecto.getDescripcion())
                .build();
        return proyectoDTO;
    }

    @Override
    public ProyectoDTO actulizarProyecto(Proyecto proyecto) {
        Proyecto project = repository.save(proyecto);
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .id(project.getId())
                .nombre(project.getNombre())
                .descripcion(proyecto.getDescripcion())
                .build();
        return proyectoDTO;
    }


    @Override
    public String eliminarProyecto(int id) {
        repository.deleteById(id);
        return "Proyecto eliminado";
    }

    @Override
    public Iterable<TareaBase> getTareaBase(int idProyecto) {
        return repository.obtenerTareasPorProyecto(idProyecto);
    }


}
