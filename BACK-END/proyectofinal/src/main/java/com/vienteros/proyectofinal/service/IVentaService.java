package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Venta;

import java.util.List;

public interface IVentaService {
    List<Venta> listarVentas(VentaDTO ventaDTO);

    Venta crearVenta(VentaDTO ventaDTO);

    Venta actualizarVenta(VentaDTO ventaDTO);

    String eliminarVenta(VentaDTO ventaDTO);
}
