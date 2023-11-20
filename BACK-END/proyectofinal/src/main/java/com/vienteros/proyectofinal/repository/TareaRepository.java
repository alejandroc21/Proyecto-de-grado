package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Tarea;
import com.vienteros.proyectofinal.model.TareaBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Integer> {


    List<Tarea> findByProyectoId(int idProyecto);
}
