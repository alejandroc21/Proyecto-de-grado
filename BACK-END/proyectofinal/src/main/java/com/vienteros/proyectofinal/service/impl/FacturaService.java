package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.model.Factura;
import com.vienteros.proyectofinal.repository.FacturaRepository;
import com.vienteros.proyectofinal.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService implements IFacturaService {

    @Autowired
    private FacturaRepository repository;


    @Override
    public Factura crearFactura(Factura factura) {
        return repository.save(factura);
    }
}
