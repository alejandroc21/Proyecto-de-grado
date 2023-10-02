package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.ProyectoDTO;
import com.vienteros.proyectofinal.DTO.UsuarioDTO;
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

    @GetMapping("/{id}")
    public Proyecto getForId(@PathVariable String id){
        return service.getForId(Integer.parseInt(id));
    }

    @PostMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> getProyectos(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.getProyectos(usuarioDTO.getId()));
    }

    @PostMapping("/crear")
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody ProyectoDTO proyectoDTO){
        return ResponseEntity.ok(service.crearProyecto(proyectoDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Proyecto> actualizarProyecto(@RequestBody ProyectoDTO proyectoDTO){
        return ResponseEntity.ok(service.actulizarProyecto(proyectoDTO));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarProyecto(@RequestBody ProyectoDTO proyectoDTO){
        return ResponseEntity.ok(service.eliminarProyecto(proyectoDTO));
    }

}
