package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.TareaDTO;
import com.vienteros.proyectofinal.model.Tarea;
import com.vienteros.proyectofinal.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tarea")
public class TareaController {

    @Autowired
    private ITareaService service;

    @GetMapping("/listar/{idProyecto}")
    public ResponseEntity<List<TareaDTO>> listarTareas(@PathVariable int idProyecto){
        return ResponseEntity.ok(service.listarTareas(idProyecto));
    }

    @PostMapping("/crear")
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody Tarea tarea){
        return ResponseEntity.ok(service.crearTarea(tarea));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TareaDTO> actualizarTarea(@RequestBody Tarea tarea){
        return ResponseEntity.ok(service.actualizarTarea(tarea));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable int id){
        return ResponseEntity.ok(service.eliminarTarea(id));
    }
}
