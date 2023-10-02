package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.model.Producto;
import com.vienteros.proyectofinal.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(service.listarProductos(productoDTO));
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> guardarProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(service.guardarProducto(productoDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(service.actualizarProducto(productoDTO));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(service.eliminarProducto(productoDTO));
    }





}
