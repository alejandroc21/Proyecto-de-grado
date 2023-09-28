package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.model.Producto;
import com.vienteros.proyectofinal.repository.ProductoRepository;
import com.vienteros.proyectofinal.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    public Producto productoPorId(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void eliminarProducto(int id) {
        repository.deleteById(id);
    }

    @Override
    public void guardarProducto(Producto producto) {
        repository.save(producto);
    }
}
