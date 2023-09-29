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

    @PostMapping("/api/registrar")
    public void guardarUsuario(@RequestBody Usuario usuario){
        service.guardarUsuario(usuario);
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registro(@RequestBody Usuario usuario){
        UsuarioDTO usuarioDTO = service.registro(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    /*
    @PostMapping("/loginu")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        boolean logeado = service.verificarUsuario(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword());
        if(logeado){
            return ResponseEntity.ok("");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("nope");
        }
    }*/


    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        UsuarioDTO usuarioDTO = service.verificar(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword());
        return ResponseEntity.ok(usuarioDTO);
    }
}
