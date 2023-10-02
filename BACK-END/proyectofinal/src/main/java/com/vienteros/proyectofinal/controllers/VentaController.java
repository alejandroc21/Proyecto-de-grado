package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Venta;
import com.vienteros.proyectofinal.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private IVentaService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Venta>> listarVentas(@RequestBody VentaDTO ventaDTO){
        return ResponseEntity.ok(service.listarVentas(ventaDTO));
    }

    @PostMapping("crear")
    public ResponseEntity<Venta> crearVenta(@RequestBody VentaDTO ventaDTO){
        return ResponseEntity.ok(service.crearVenta(ventaDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Venta> actualizarVenta(@RequestBody VentaDTO ventaDTO){
        return ResponseEntity.ok(service.actualizarVenta(ventaDTO));
    }

    @DeleteMapping("/elimiar")
    public ResponseEntity<String> eliminarVenta(@RequestBody VentaDTO ventaDTO){
        return ResponseEntity.ok(service.eliminarVenta(ventaDTO));
    }
}
