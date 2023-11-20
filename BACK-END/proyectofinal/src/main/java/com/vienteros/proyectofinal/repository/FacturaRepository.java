package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Integer> {
}
