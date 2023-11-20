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

    @GetMapping("/listar-todo/{idUsuario}")
    public ResponseEntity<List<VentaDTO>> listasTodosVentas(@PathVariable int idUsuario){
        return ResponseEntity.ok(service.listarTodosVentas(idUsuario));
    }

    @GetMapping("/listar/{idProyecto}")
    public ResponseEntity<List<VentaDTO>> listarVentas(@PathVariable int idProyecto){
        return ResponseEntity.ok(service.listarVentas(idProyecto));
    }

    @PostMapping("/crear")
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody Venta venta){
        return ResponseEntity.ok(service.crearVenta(venta));
    }

    //requiere el id_proyecto e id_factura
    @PostMapping("/crear-multiple")
    public ResponseEntity<List<VentaDTO>> crearVentaMultiple(@RequestBody List<Venta> ventas){
        return ResponseEntity.ok(service.crearVentaMultiple(ventas));
    }





    @PutMapping("/actualizar")
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestBody Venta venta){
        return ResponseEntity.ok(service.actualizarVenta(venta));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable int id){
        return ResponseEntity.ok(service.eliminarVenta(id));
    }
}
