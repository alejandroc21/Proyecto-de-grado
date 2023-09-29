package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.UsuarioDTO;
import com.vienteros.proyectofinal.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Usuario UsuarioPorId(int id);

    UsuarioDTO login(String email, String password);

    UsuarioDTO registro(Usuario usuario);
}
