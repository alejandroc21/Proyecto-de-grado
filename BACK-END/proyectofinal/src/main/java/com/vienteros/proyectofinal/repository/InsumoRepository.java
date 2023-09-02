package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Insumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends CrudRepository<Insumo,Integer> {
}