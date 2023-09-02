package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.model.Producto;
import com.vienteros.proyectofinal.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService service;

    @GetMapping("/api/productos")
    public List<Producto> listarProductos(){
        return service.listarProductos();
    }

    @GetMapping("/api/productos/{id}")
    public Producto productoPorId(@PathVariable String id){
        return service.productoPorId(Integer.parseInt(id));
    }

    @DeleteMapping("/api/usuarios/{id}")
    public void eliminarProducto(@PathVariable String id){
        service.eliminarProducto(Integer.parseInt(id));
    }

    @PostMapping("/api/productos/")
    public void guardarProducto(@RequestBody Producto producto){
        service.guardarProducto(producto);
    }



}
