package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Planeacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneacionRepository extends CrudRepository<Planeacion, Integer> {
}
