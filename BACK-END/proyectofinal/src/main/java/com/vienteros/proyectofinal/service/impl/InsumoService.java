package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.model.Insumo;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.repository.InsumoRepository;
import com.vienteros.proyectofinal.service.IInsumoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsumoService implements IInsumoService{

    @Autowired
    private InsumoRepository repository;


    @Override
    public List<InsumoDTO> listarInsumos(int idProyecto) {
        List<Insumo> insumos = repository.findByProyectoId(idProyecto);
        List<InsumoDTO> insumosDTO = insumos.stream().map(insumo -> {
            InsumoDTO insumoDTO = InsumoDTO.builder()
                    .id(insumo.getId())
                    .nombre(insumo.getNombre())
                    .cantidad(insumo.getCantidad())
                    .precio(insumo.getPrecio())
                    .build();
            return insumoDTO;
        }).collect(Collectors.toList());
        return insumosDTO;
    }

    @Override
    public InsumoDTO guardarInsumo(Insumo insumo) {
        return null;
    }

    @Override
    public InsumoDTO actualizarInsumo(Insumo insumo) {
        return null;
    }


    @Override
    public String eliminarInsumo(int id) {
        repository.deleteById(id);
        return "se elimino el insumo exitosamente";
    }


}
