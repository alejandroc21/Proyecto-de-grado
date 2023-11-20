package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.model.Factura;
import com.vienteros.proyectofinal.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @PostMapping("/crear")
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura){
        return  ResponseEntity.ok(facturaService.crearFactura(factura));
    }
}
