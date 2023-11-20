package com.vienteros.proyectofinal.repository;

import com.vienteros.proyectofinal.model.Categoria;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Tarea;
import com.vienteros.proyectofinal.model.TareaBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto,Integer> {

    @Query("SELECT t FROM Proyecto p JOIN p.categoria c JOIN c.tareasBase t WHERE p.id = :idProyecto")
    List<TareaBase> obtenerTareasPorProyecto(@Param("idProyecto") int idProyecto);
    List<Proyecto> findByUsuarioId(int idUsuario);


}
