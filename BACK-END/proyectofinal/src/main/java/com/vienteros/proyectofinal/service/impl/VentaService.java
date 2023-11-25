package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Factura;
import com.vienteros.proyectofinal.model.Proyecto;
import com.vienteros.proyectofinal.model.Venta;
import com.vienteros.proyectofinal.repository.FacturaRepository;
import com.vienteros.proyectofinal.repository.VentaRepository;
import com.vienteros.proyectofinal.service.IFacturaService;
import com.vienteros.proyectofinal.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private IFacturaService facturaService;

    @Override
    public List<VentaDTO> listarTodosVentas(int idUsuario) {
        List<Venta> ventas = repository.findByProyectoUsuarioId(idUsuario);
        return ventas.stream().map(venta -> {
            VentaDTO ventaDTO = VentaDTO.builder()
                    .id(venta.getId())
                    .nombre(venta.getNombre())
                    .precio(venta.getPrecio())
                    .cantidad(venta.getCantidad())
                    .fecha(venta.getFecha())
                    .FacturaId(venta.getFactura().getId())
                    .build();
            return ventaDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> listarVentas(int idProyecto) {
        List<Venta> ventas = repository.findByProyectoId(idProyecto);
        List<VentaDTO> ventasDTO = ventas.stream().map(venta -> {
            VentaDTO ventaDTO = VentaDTO.builder()
                    .id(venta.getId())
                    .nombre(venta.getNombre())
                    .precio(venta.getPrecio())
                    .cantidad(venta.getCantidad())
                    .fecha(venta.getFecha())
                    .FacturaId(venta.getFactura().getId())
                    .build();
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
    public ResponseEntity<Resource> crearVentaMultiple(List<Venta> ventas) {
        Factura factura  = ventas.get(0).getFactura();

        Factura facturaGuardada = facturaService.crearFactura(factura);


        //int facturaId = facturaGuardada.getId();

        //ventas.forEach(venta -> venta.setFacturaId(facturaId));
        ventas.forEach(venta -> venta.setFactura(facturaGuardada));

        List<Venta> ventasGuardadas = new ArrayList<>();
        repository.saveAll(ventas).forEach(ventasGuardadas::add);

        List<VentaDTO> ventaDTOList = new ArrayList<>();
        for (Venta ventaGuardada : ventasGuardadas) {
            VentaDTO ventaDTO = new VentaDTO();
            ventaDTO.setId(ventaGuardada.getId());
            ventaDTO.setNombre(ventaGuardada.getNombre());
            ventaDTO.setPrecio(ventaGuardada.getPrecio());
            ventaDTO.setCantidad(ventaGuardada.getCantidad());
            ventaDTO.setFecha(ventaGuardada.getFecha());
            ventaDTO.setFacturaId(ventaGuardada.getFactura().getId());

            ventaDTOList.add(ventaDTO);
        }

        return facturaService.exportFactura(ventaDTOList, facturaGuardada);

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
