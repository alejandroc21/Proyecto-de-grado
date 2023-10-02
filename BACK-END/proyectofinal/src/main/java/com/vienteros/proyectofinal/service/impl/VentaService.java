package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Venta;
import com.vienteros.proyectofinal.repository.VentaRepository;
import com.vienteros.proyectofinal.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository repository;

    @Override
    public List<Venta> listarVentas(VentaDTO ventaDTO) {
        List<Venta> ventas = repository.findByProyectoId(ventaDTO.getIdProyecto());
        return ventas;
    }

    @Override
    public Venta crearVenta(VentaDTO ventaDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(ventaDTO.getIdProyecto());
        Venta venta = Venta.builder()
                .nombre(ventaDTO.getNombre())
                .precio(ventaDTO.getPrecio())
                .cantidad(ventaDTO.getCantidad())
                .fecha(ventaDTO.getFecha())
                .proyecto(proyecto).build();
        return repository.save(venta);
    }

    @Override
    public Venta actualizarVenta(VentaDTO ventaDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(ventaDTO.getIdProyecto());
        Venta venta = Venta.builder()
                .id(ventaDTO.getId())
                .nombre(ventaDTO.getNombre())
                .precio(ventaDTO.getPrecio())
                .cantidad(ventaDTO.getCantidad())
                .fecha(ventaDTO.getFecha())
                .proyecto(proyecto).build();
        return repository.save(venta);
    }

    @Override
    public String eliminarVenta(VentaDTO ventaDTO) {
        repository.deleteById(ventaDTO.getId());
        return "venta eliminada";
    }
}
