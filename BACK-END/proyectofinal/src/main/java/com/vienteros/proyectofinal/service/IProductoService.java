package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.model.Producto;

import java.util.List;

public interface IProductoService {


    List<ProductoDTO> listarProductos(int idProyecto);

    ProductoDTO guardarProducto(Producto producto);

    ProductoDTO actualizarProducto(Producto producto);

    String eliminarProducto(int id);

    List<ProductoDTO> listarTodosProductos(int idUsuario);

    String actualizarProductoMultiple(List<Producto> productos);
}
