package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.model.Insumo;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.repository.InsumoRepository;
import com.vienteros.proyectofinal.service.IInsumoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService implements IInsumoService{

    @Autowired
    private InsumoRepository repository;


    @Override
    public List<Insumo> listarInsumos(InsumoDTO insumoDTO) {
        List<Insumo> insumos = repository.findByProyectoId(insumoDTO.getIdProyecto());
        return insumos;
    }

    @Override
    public Insumo guardarInsumo(InsumoDTO insumoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(insumoDTO.getIdProyecto());
        Insumo insumo = Insumo.builder()
                .nombre(insumoDTO.getNombre())
                .cantidad(insumoDTO.getCantidad())
                .precio(insumoDTO.getPrecio())
                .proyecto(proyecto).build();

        return repository.save(insumo);
    }

    @Override
    public String eliminarInsumo(InsumoDTO insumoDTO) {
        repository.deleteById(insumoDTO.getId());
        return "se elimino el insumo exitosamente";
    }

    @Override
    public Insumo actualizarInsumo(InsumoDTO insumoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(insumoDTO.getId());
        Insumo insumo = Insumo.builder()
                .id(insumoDTO.getId())
                .nombre(insumoDTO.getNombre())
                .cantidad(insumoDTO.getCantidad())
                .precio(insumoDTO.getPrecio())
                .proyecto(proyecto).build();

        return repository.save(insumo);
    }
}
