package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Venta;
import com.vienteros.proyectofinal.repository.VentaRepository;
import com.vienteros.proyectofinal.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository repository;

    @Override
    public List<VentaDTO> listarVentas(int idProyecto) {
        List<Venta> ventas = repository.findAllByProductoProyectoId(idProyecto);
        List<VentaDTO> ventasDTO = ventas.stream().map(venta -> {
            VentaDTO ventaDTO = VentaDTO.builder()
                    .id(venta.getId())
                    .nombre(venta.getProducto().getNombre())
                    .precio(venta.getPrecio())
                    .cantidad(venta.getCantidad())
                    .fecha(venta.getFecha()).build();
            return ventaDTO;
        }).collect(Collectors.toList());
        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(Venta venta) {
        Venta ventatmp = repository.save(venta);
        VentaDTO ventaDTO = VentaDTO.builder()
                .id(ventatmp.getId())
                .nombre(ventatmp.getNombre())
                .precio(venta.getPrecio())
                .cantidad(venta.getCantidad())
                .fecha(venta.getFecha()).build();
        return ventaDTO;
    }

    @Override
    public VentaDTO actualizarVenta(Venta venta) {
        Venta ventatmp = repository.save(venta);
        VentaDTO ventaDTO = VentaDTO.builder()
                .id(ventatmp.getId())
                .nombre(ventatmp.getNombre())
                .precio(venta.getPrecio())
                .cantidad(venta.getCantidad())
                .fecha(venta.getFecha()).build();
        return ventaDTO;
    }


    @Override
    public String eliminarVenta(int id) {
        repository.deleteById(id);
        return "venta eliminada";
    }
}
