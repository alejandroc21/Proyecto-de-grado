package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {

    @Autowired
    private IProyectoService service;

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<ProyectoDTO>> getProyectos(@PathVariable int idUsuario){
        return ResponseEntity.ok(service.getProyectos(idUsuario));
    }


    @PostMapping("/crear")
    public ResponseEntity<ProyectoDTO> guardarProyecto(@RequestBody Proyecto proyecto){
        return  ResponseEntity.ok(service.guardarProyecto(proyecto));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProyectoDTO> actualizarProyecto(@RequestBody Proyecto proyecto){
        return ResponseEntity.ok(service.actulizarProyecto(proyecto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable int  id){
        return ResponseEntity.ok(service.eliminarProyecto(id));
    }

}
