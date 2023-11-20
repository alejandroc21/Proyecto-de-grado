package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    List<Cliente> findByProyectoId(int idProyecto);

    List<Cliente> findByProyectoUsuarioId(int idUsuario);
}
