package com.vienteros.proyectofinal.service;

import java.io.ByteArrayInputStream;

public interface IExcelService {
    ByteArrayInputStream exportAll(int idUsuario) throws Exception;
}
