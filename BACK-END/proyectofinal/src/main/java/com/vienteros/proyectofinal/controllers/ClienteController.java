package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.DTO.ClienteDTO;
import com.vienteros.proyectofinal.model.Cliente;
import com.vienteros.proyectofinal.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping("/listar-todo/{idUsuario}")
    public ResponseEntity<List<ClienteDTO>> listarClientesUsuario(@PathVariable int idUsuario){
        return ResponseEntity.ok(service.listarClientesUsuario(idUsuario));
    }

    @GetMapping("/listar/{idProyecto}")
    public ResponseEntity<List<ClienteDTO>> listarClientes(@PathVariable int idProyecto){
        return ResponseEntity.ok(service.listarClientes(idProyecto));
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(service.crearCliente(cliente));
    }
}
