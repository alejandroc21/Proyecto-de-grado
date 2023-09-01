package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {
}
