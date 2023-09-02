package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.model.Insumo;

import java.util.List;

public interface IInsumoService {
    List<Insumo> listarInsumos();

    Insumo insumoPorId(int id);

    void eliminarInsumo(int id);


    void guardarInsumo(Insumo insumo);

}
