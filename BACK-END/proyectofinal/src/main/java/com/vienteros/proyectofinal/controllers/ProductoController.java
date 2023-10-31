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

    @GetMapping("/listar-todo/{idUsuario}")
    public ResponseEntity<List<ProductoDTO>> listarTodosProductos(@PathVariable int idUsuario){
        return  ResponseEntity.ok(service.listarTodosProductos(idUsuario));
    }

    @GetMapping("/listar/{idProyecto}")
    public ResponseEntity<List<ProductoDTO>> listarProductos(@PathVariable int idProyecto){
        return ResponseEntity.ok(service.listarProductos(idProyecto));
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> guardarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(service.guardarProducto(producto));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(service.actualizarProducto(producto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id){
        return ResponseEntity.ok(service.eliminarProducto(id));
    }

}
