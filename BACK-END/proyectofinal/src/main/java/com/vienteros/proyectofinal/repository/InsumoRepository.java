package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Insumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends CrudRepository<Insumo,Integer> {

    List<Insumo> findByProyectoId(int idProyecto);

    List<Insumo> findByProyectoUsuarioId(int idUsuario);
}
