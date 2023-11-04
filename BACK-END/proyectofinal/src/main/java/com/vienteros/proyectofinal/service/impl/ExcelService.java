package com.vienteros.proyectofinal.service.impl;

import com.vienteros.proyectofinal.DTO.InsumoDTO;
import com.vienteros.proyectofinal.DTO.ProductoDTO;
import com.vienteros.proyectofinal.DTO.VentaDTO;
import com.vienteros.proyectofinal.service.IExcelService;
import com.vienteros.proyectofinal.service.IInsumoService;
import com.vienteros.proyectofinal.service.IProductoService;
import com.vienteros.proyectofinal.service.IVentaService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ExcelService implements IExcelService {


    @Autowired
    private IVentaService ventaService;
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IInsumoService insumoService;


    @Override
    public ByteArrayInputStream exportAll(int idUsuario) throws Exception{

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet Ventasheet = workbook.createSheet("Usuario-Ventas");
        Sheet Productosheet = workbook.createSheet("Usuario-Productos");
        Sheet InsumoSheet = workbook.createSheet("Usuario-Insumos");

        Row Ventarow = Ventasheet.createRow(0);
        Row Productorow = Productosheet.createRow(0);
        Row Insumorow = InsumoSheet.createRow(0);

        String[] Ventacolumns = {"Id", "Nombre", "Precio", "Cantidad", "Fecha"};
        String[] Productocolumns = {"Id", "Nombre", "Descripcion", "Cantidad inicial", "Cantidad final", "precio", "fecha"};
        String[] Insumocolums = {"Id", "Nombre", "Precio", "Cantidad", "Fecha"};

        // Crear un estilo de celda con formato de negrita
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);


        for (int i = 0; i < Ventacolumns.length; i++) {
            Cell cell = Ventarow.createCell(i);
            cell.setCellValue(Ventacolumns[i]);
            cell.setCellStyle(headerCellStyle);
            Ventasheet.autoSizeColumn(i);
        }

        for (int i = 0; i < Productocolumns.length; i++) {
            Cell cell = Productorow.createCell(i);
            cell.setCellValue(Productocolumns[i]);
            cell.setCellStyle(headerCellStyle);
            Productosheet.autoSizeColumn(i);
        }

        for (int i = 0; i<Insumocolums.length; i++){
            Cell cell = Insumorow.createCell(i);
            cell.setCellValue(Insumocolums[i]);
            cell.setCellStyle(headerCellStyle);
            InsumoSheet.autoSizeColumn(i);
        }

        //Ventas

        List<VentaDTO> ventasDTO = ventaService.listarTodosVentas(idUsuario);
        int initRow=1;

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        for(VentaDTO ventaDTO: ventasDTO){
            Ventarow = Ventasheet.createRow(initRow);
            Ventarow.createCell(0).setCellValue(ventaDTO.getId());
            Ventarow.createCell(1).setCellValue(ventaDTO.getNombre());
            Ventarow.createCell(2).setCellValue(ventaDTO.getPrecio());
            Ventarow.createCell(3).setCellValue(ventaDTO.getCantidad());

            Cell dateCell = Ventarow.createCell(4);
            dateCell.setCellValue(ventaDTO.getFecha());
            dateCell.setCellStyle(dateCellStyle);

            initRow++;
        }
        Ventasheet.autoSizeColumn(1);
        Ventasheet.autoSizeColumn(4);

        //PRODUCTO

        List<ProductoDTO> productosDTO = productoService.listarTodosProductos(idUsuario);
        int productoInit=1;

        for(ProductoDTO productoDTO: productosDTO){
            Productorow = Productosheet.createRow(productoInit);
            Productorow.createCell(0).setCellValue(productoDTO.getId());
            Productorow.createCell(1).setCellValue(productoDTO.getNombre());
            Productorow.createCell(2).setCellValue(productoDTO.getDescripcion());
            Productorow.createCell(3).setCellValue(productoDTO.getCantidadInicial());
            Productorow.createCell(4).setCellValue(productoDTO.getCantidadFinal());
            Productorow.createCell(5).setCellValue(productoDTO.getPrecio());

            Cell productoDate = Productorow.createCell(6);
            productoDate.setCellValue(productoDTO.getFecha());
            productoDate.setCellStyle(dateCellStyle);
            productoInit++;
        }
        Productosheet.autoSizeColumn(1);
        Productosheet.autoSizeColumn(2);
        Productosheet.autoSizeColumn(6);


        //Insumos

        List<InsumoDTO> insumosDTO = insumoService.listarTodosInsumos(idUsuario);
        int insumoInit=1;

        for (InsumoDTO insumoDTO: insumosDTO){
            Insumorow = InsumoSheet.createRow(insumoInit);
            Insumorow.createCell(0).setCellValue(insumoDTO.getId());
            Insumorow.createCell(1).setCellValue(insumoDTO.getNombre());
            Insumorow.createCell(2).setCellValue(insumoDTO.getPrecio());
            Insumorow.createCell(3).setCellValue(insumoDTO.getCantidad());

            Cell insumoDate = Insumorow.createCell(4);
            insumoDate.setCellValue(insumoDTO.getFecha());
            insumoDate.setCellStyle(dateCellStyle);
            insumoInit++;
        }
        InsumoSheet.autoSizeColumn(1);
        InsumoSheet.autoSizeColumn(4);


        //Crear excel
        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }



    /*
    @Override
    public ByteArrayInputStream exportAll() throws Exception{
        String[] columns = {"Id", "nombre", "precio", "cantidad", "fecha"};
        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Usuario-Ventas");
        Row row = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        List<VentaDTO> ventasDTO = ventaService.listarTodosVentas(1);
        int initRow=1;
        for(VentaDTO ventaDTO: ventasDTO){
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(ventaDTO.getId());
            row.createCell(1).setCellValue(ventaDTO.getNombre());
            row.createCell(2).setCellValue(ventaDTO.getPrecio());
            row.createCell(3).setCellValue(ventaDTO.getCantidad());
           // row.createCell(4).setCellValue(ventaDTO.getFecha());
            String formattedDate = dateFormat.format(ventaDTO.getFecha());
            row.createCell(4).setCellValue(formattedDate);
            initRow++;
        }
        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }*/
}
