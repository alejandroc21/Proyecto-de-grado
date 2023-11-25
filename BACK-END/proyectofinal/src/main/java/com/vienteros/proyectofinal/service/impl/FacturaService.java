package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.model.Cliente;
import com.vienteros.proyectofinal.model.Factura;
import com.vienteros.proyectofinal.repository.ClienteRepository;
import com.vienteros.proyectofinal.repository.FacturaRepository;
import com.vienteros.proyectofinal.service.IFacturaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class FacturaService implements IFacturaService {



    @Autowired
    private FacturaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Factura crearFactura(Factura factura) {
        return repository.save(factura);
    }


    @Override
    public ResponseEntity<Resource> exportFactura(List<VentaDTO> ventaDTOList, Factura factura){
        Optional<Cliente> optCliente = clienteRepository.findById(factura.getCliente().getId());

        double precioTotal =  calcularPrecioTotal(ventaDTOList);

        if(optCliente.isPresent()) {
            try {
                final Cliente cliente = optCliente.get();
                final File file = ResourceUtils.getFile("classpath:report/exportInvoice.jasper");
                final File imgLogo = ResourceUtils.getFile("classpath:report/logo.jpg");
                final JasperReport report = (JasperReport) JRLoader.loadObject(file);


                final HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("nombreCliente", cliente.getNombre());
                parameters.put("imgLogo", new FileInputStream(imgLogo));
                parameters.put("documentoCliente", cliente.getDocumento());
                parameters.put("numeroFactura",  String.valueOf(factura.getId()));
                parameters.put("total", precioTotal);
                parameters.put("dsInvoice", new JRBeanCollectionDataSource((Collection<?>) ventaDTOList));

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
                StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder.append(cliente.getId())
                                .append("generateDate:").append(sdf).append(".pdf").toString()).build();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                return ResponseEntity.ok().contentLength((long) reporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reporte));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            return ResponseEntity.noContent().build();
        }

        return null;

    }


    private  double calcularPrecioTotal(List<VentaDTO> ventas) {
        double precioTotal = 0.0;

        for (VentaDTO venta : ventas) {
            // Calcular el precio total de cada venta y sumarlo al total
            precioTotal += venta.getCantidad() * venta.getPrecio();
        }

        return precioTotal;
    }


}
