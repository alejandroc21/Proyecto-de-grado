package com.vienteros.proyectofinal.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public interface IExcelService {
    ByteArrayInputStream exportAll(int idUsuario) throws Exception;

    ByteArrayInputStream exportProject(int idProyecto) throws Exception;

    ResponseEntity<Resource> factura(int idFactura);


}
