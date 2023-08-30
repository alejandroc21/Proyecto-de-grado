package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> listarTodos(){
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public Usuario UsuarioPorId(int id) {
        return (Usuario) repository.findById(id).get();
    }
}
