package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    void guardarUsuario(Usuario usuario);

    Usuario UsuarioPorId(int id);
}
