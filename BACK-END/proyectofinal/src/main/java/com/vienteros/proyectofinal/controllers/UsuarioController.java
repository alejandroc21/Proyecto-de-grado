package com.vienteros.proyectofinal.controllers;



import com.vienteros.proyectofinal.DTO.UsuarioDTO;
import com.vienteros.proyectofinal.DTO.UsuarioLoginDTO;
import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping("/api/usuarios")
    public List<Usuario> listarTodos(){
        return service.listarTodos();
    }

    @GetMapping("/api/usuarios/{id}")
    public Usuario UsuarioPorId(@PathVariable String id){
        return service.UsuarioPorId(Integer.parseInt(id));
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registro(@RequestBody Usuario usuario){
        UsuarioDTO usuarioDTO = service.registro(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        UsuarioDTO usuarioDTO = service.login(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword());
        return ResponseEntity.ok(usuarioDTO);
    }
}
