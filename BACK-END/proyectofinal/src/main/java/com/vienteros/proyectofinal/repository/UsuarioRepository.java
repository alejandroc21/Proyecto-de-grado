package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
