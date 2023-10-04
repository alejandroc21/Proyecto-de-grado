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

    @GetMapping("/listar/{idProyecto}")
    public ResponseEntity<List<InsumoDTO>> listarInsumos(@PathVariable int idProyecto){
        return ResponseEntity.ok(service.listarInsumos(idProyecto));
    }

    @PostMapping("/crear")
    public ResponseEntity<InsumoDTO> guardarInsumo(@RequestBody Insumo insumo){
        return ResponseEntity.ok(service.guardarInsumo(insumo));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<InsumoDTO> actualizarInsumo(@RequestBody Insumo insumo){
        return ResponseEntity.ok(service.actualizarInsumo(insumo));
    }


    @DeleteMapping("/elimiar/{id}")
    public ResponseEntity<String> eliminarInsumo(@PathVariable int id){
        return ResponseEntity.ok(service.eliminarInsumo(id));
    }
}
