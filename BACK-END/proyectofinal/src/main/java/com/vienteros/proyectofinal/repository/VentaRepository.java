package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Integer> {
    List<Venta> findByProyectoId(int idProyecto);

    //List<Venta> findAllByProductoProyectoId(int idProyecto);
}
