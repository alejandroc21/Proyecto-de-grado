package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.model.Insumo;

import java.util.List;

public interface IInsumoService {

    List<InsumoDTO> listarInsumos(int idProyecto);

    InsumoDTO guardarInsumo(Insumo insumo);

    InsumoDTO actualizarInsumo(Insumo insumo);

    String eliminarInsumo(int id);
}
