package com.vienteros.proyectofinal.controllers;

import com.vienteros.proyectofinal.service.IExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

@Controller
@CrossOrigin
@RequestMapping("/api/export")
public class ExcelController {

    @Autowired
    private IExcelService excelService;

    @GetMapping("/excel-all/{idUsuario}")
    public ResponseEntity<InputStreamResource> exportAll(@PathVariable int idUsuario) throws Exception{
        ByteArrayInputStream stream = excelService.exportAll(idUsuario);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=usuariodata.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }


}
