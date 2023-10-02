package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.model.Insumo;
import com.vienteros.proyectofinal.service.IInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/insumo")
public class InsumoController {

    @Autowired
    private IInsumoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Insumo>> listarInsumos(@RequestBody InsumoDTO insumoDTO){
        return ResponseEntity.ok(service.listarInsumos(insumoDTO));
    }

    @PostMapping("/crear")
    public ResponseEntity<Insumo> guardarInsumo(@RequestBody InsumoDTO insumoDTO){
        return ResponseEntity.ok(service.guardarInsumo(insumoDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Insumo> actualizarInsumo(@RequestBody InsumoDTO insumoDTO){
        return ResponseEntity.ok(service.actualizarInsumo(insumoDTO));
    }

    @DeleteMapping("/elimiar")
    public ResponseEntity<String> eliminarInsumo(@RequestBody InsumoDTO insumoDTO){
        return ResponseEntity.ok(service.eliminarInsumo(insumoDTO));
    }

}
