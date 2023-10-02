package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.model.Producto;

import java.util.List;

public interface IProductoService {


    List<Producto> listarProductos(ProductoDTO productoDTO);

    Producto guardarProducto(ProductoDTO productoDTO);

    Producto actualizarProducto(ProductoDTO productoDTO);

    String eliminarProducto(ProductoDTO productoDTO);
}
