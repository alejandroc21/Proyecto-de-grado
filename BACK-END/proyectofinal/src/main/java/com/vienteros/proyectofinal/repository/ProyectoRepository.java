package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto,Integer> {


    List<Proyecto> findByUsuarioId(int idUsuario);
}
