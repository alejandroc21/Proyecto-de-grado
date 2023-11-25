package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Venta;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IVentaService {
    List<VentaDTO> listarVentas(int idProyecto);

    VentaDTO crearVenta(Venta venta);

    //List<VentaDTO> crearVentaMultiple(List<Venta> ventas);

    ResponseEntity<Resource> crearVentaMultiple(List<Venta> ventas);

    VentaDTO actualizarVenta(Venta venta);

    String eliminarVenta(int id);

    List<VentaDTO> listarTodosVentas(int idUsuario);


}
