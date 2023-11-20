package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Categoria;
import com.vienteros.proyectofinal.model.TareaBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaBaseRepository extends CrudRepository<TareaBase, Integer> {


    List<TareaBase> findByCategoria(Categoria categoria);
}
