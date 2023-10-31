package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByProyectoId(int idProyecto);

    List<Producto> findByProyectoUsuarioId(int idUsuario);
}
