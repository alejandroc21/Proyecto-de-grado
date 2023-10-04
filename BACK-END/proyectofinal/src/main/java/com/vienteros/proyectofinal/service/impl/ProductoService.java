package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.model.Producto;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.repository.ProductoRepository;
import com.vienteros.proyectofinal.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repository;


    @Override
    public List<ProductoDTO> listarProductos(int idProyecto) {
        List<Producto> productos = repository.findByProyectoId(idProyecto);
        List<ProductoDTO> example = new ArrayList<>();
        return example;
    }

    @Override
    public ProductoDTO guardarProducto(Producto producto) {
        Producto product = repository.save(producto);
        ProductoDTO productoDTO = ProductoDTO.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .cantidadInicial(product.getCantidadInicial())
                .cantidadFinal(product.getCantidadFinal())
                .precio(product.getPrecio())
                .fecha(product.getFecha())
                .build();

        return productoDTO;

    }

    @Override
    public ProductoDTO actualizarProducto(Producto producto) {
        Producto product = repository.save(producto);
        ProductoDTO productoDTO = ProductoDTO.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .cantidadInicial(product.getCantidadInicial())
                .cantidadFinal(product.getCantidadFinal())
                .precio(product.getPrecio())
                .fecha(product.getFecha())
                .build();

        return productoDTO;
    }


    @Override
    public String eliminarProducto(int id) {
        repository.deleteById(id);
        return "producto eliminado";
    }
}
