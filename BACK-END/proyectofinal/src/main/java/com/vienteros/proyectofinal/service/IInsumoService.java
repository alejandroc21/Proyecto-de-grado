package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.model.Insumo;

import java.util.List;

public interface IInsumoService {
    List<Insumo> listarInsumos(InsumoDTO insumoDTO);

    Insumo guardarInsumo(InsumoDTO insumoDTO);

    String eliminarInsumo(InsumoDTO insumoDTO);

    Insumo actualizarInsumo(InsumoDTO insumoDTO);
}
