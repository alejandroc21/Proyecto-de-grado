package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.model.Insumo;
import com.vienteros.proyectofinal.service.IInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class InsumoController {

    @Autowired
    private IInsumoService service;

    @GetMapping("/api/insumos")
    public List<Insumo> listarInsumos(){
        return service.listarInsumos();
    }

    @GetMapping("/api/insumos/{id}")
    public Insumo insumoPorId(@PathVariable String id){
        return service.insumoPorId(Integer.parseInt(id));
    }

    @DeleteMapping("/api/insumos/{id}")
    public void eliminarInsumo(@PathVariable String id){
        service.eliminarInsumo(Integer.parseInt(id));
    }

    @PostMapping("api/insumos/")
    public void guardarInsumo(@RequestBody Insumo insumo){
        service.guardarInsumo(insumo);
    }
}
