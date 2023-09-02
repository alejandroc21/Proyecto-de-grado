package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.model.Insumo;
import com.vienteros.proyectofinal.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService implements IInsumoService{

    @Autowired
    private InsumoRepository repository;

    @Override
    public List<Insumo> listarInsumos() {
        return (List<Insumo>) repository.findAll();
    }

    @Override
    public Insumo insumoPorId(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void eliminarInsumo(int id) {
        repository.deleteById(id);
    }

    @Override
    public void guardarInsumo(Insumo insumo) {
        repository.save(insumo);
    }
}
