package com.vienteros.proyectofinal.service;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Factura;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFacturaService {
    Factura crearFactura(Factura factura);

    ResponseEntity<Resource> exportFactura(List<VentaDTO> ventaDTOList, Factura factura);
}
