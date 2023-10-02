package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.repository.ProyectoRepository;
import com.vienteros.proyectofinal.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private ProyectoRepository repository;

    @Override
    public Proyecto getForId(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Proyecto> getProyectos(int idUsuario) {
        List<Proyecto> proyectos = repository.findByUsuarioId(idUsuario);
        return  proyectos;
    }

    @Override
    public Proyecto crearProyecto(ProyectoDTO proyectoDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(proyectoDTO.getIdUsuario());
        Proyecto proyecto = Proyecto.builder()
                .nombre(proyectoDTO.getNombre())
                .diasEstimados(proyectoDTO.getDiasEstimados())
                .usuario(usuario).build();
        return repository.save(proyecto);
    }

    @Override
    public Proyecto actulizarProyecto(ProyectoDTO proyectoDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(proyectoDTO.getIdUsuario());
        Proyecto proyecto = Proyecto.builder()
                .id(proyectoDTO.getId())
                .nombre(proyectoDTO.getNombre())
                .diasEstimados(proyectoDTO.getDiasEstimados())
                .usuario(usuario).build();

        return repository.save(proyecto);
    }

    @Override
    public String eliminarProyecto(ProyectoDTO proyectoDTO) {
        repository.deleteById(proyectoDTO.getId());
        return "Proyecto eliminado";
    }

}
