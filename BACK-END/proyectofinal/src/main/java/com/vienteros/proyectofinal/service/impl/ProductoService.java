package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.model.Producto;
import com.vienteros.proyectofinal.model.Proyecto;
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
    public List<Producto> listarProductos(ProductoDTO productoDTO) {
        List<Producto> productos = repository.findByProyectoId(productoDTO.getIdProyecto());
        return productos;
    }

    @Override
    public Producto guardarProducto(ProductoDTO productoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(productoDTO.getIdProyecto());
        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .cantidadInicial(productoDTO.getCantidadInicial())
                .cantidadFinal(productoDTO.getCantidadFinal())
                .precio(productoDTO.getPrecio())
                .fecha(productoDTO.getFecha())
                .proyecto(proyecto).build();
        return repository.save(producto);
    }

    @Override
    public Producto actualizarProducto(ProductoDTO productoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(productoDTO.getIdProyecto());
        Producto producto = Producto.builder()
                .id(proyecto.getId())
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .cantidadInicial(productoDTO.getCantidadInicial())
                .cantidadFinal(productoDTO.getCantidadFinal())
                .precio(productoDTO.getPrecio())
                .fecha(productoDTO.getFecha())
                .proyecto(proyecto).build();
        return repository.save(producto);
    }

    @Override
    public String eliminarProducto(ProductoDTO productoDTO) {
        repository.deleteById(productoDTO.getId());
        return "producto eliminado";
    }
}
