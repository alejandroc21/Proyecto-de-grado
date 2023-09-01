package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.model.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> listarProductos();

    Producto productoPorId(int id);

    void eliminarProducto(int id);

    void guardarProducto(Producto producto);
}
