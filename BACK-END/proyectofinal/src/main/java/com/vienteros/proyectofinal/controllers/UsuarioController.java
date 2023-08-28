package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping("/api/usuarios")
    public List<Usuario> listarTodos(){
        return service.listarTodos();
    }

    @PostMapping("/api/registrar")
    public void guardarUsuario(@RequestBody Usuario usuario){
        service.guardarUsuario(usuario);
    }


}
