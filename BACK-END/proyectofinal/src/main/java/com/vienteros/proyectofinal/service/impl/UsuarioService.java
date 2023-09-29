package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.UsuarioDTO;
import com.vienteros.proyectofinal.exception.GlobalHandlerException;
import com.vienteros.proyectofinal.exception.UsuarioNotFoundException;
import com.vienteros.proyectofinal.model.Usuario;
import com.vienteros.proyectofinal.repository.UsuarioRepository;
import com.vienteros.proyectofinal.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean verificarUsuario(String email, String password) {
        Optional<Usuario> usuarioOptional = repository.findByEmailAndPassword(email, password);

        return usuarioOptional.isPresent();
    }

    public UsuarioDTO verificar(String email, String password){
        Optional<Usuario> usuarioOptional = repository.findByEmailAndPassword(email, password);
        if (usuarioOptional.isPresent()){

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuarioOptional.get().getId());
            usuarioDTO.setNombre(usuarioOptional.get().getNombre());
            usuarioDTO.setEmail(usuarioOptional.get().getEmail());
            return usuarioDTO;
        }else {
            throw new UsuarioNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public UsuarioDTO registro(Usuario usuario) {
        if(!repository.existsByEmail(usuario.getEmail())){
            repository.save(usuario);
            return verificar(usuario.getEmail(), usuario.getPassword());
        }else{
            throw new UsuarioNotFoundException("este email ya existe");
        }

    }
}
