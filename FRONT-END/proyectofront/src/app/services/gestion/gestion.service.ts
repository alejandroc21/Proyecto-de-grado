import { Injectable } from "@angular/core";
import { VentaService } from "../inventario/venta.service";
import { InsumoService } from "../inventario/insumo.service";
import { ProductoService } from "../inventario/producto.service";
import { Venta } from "src/app/models/venta";
import { Insumo } from "src/app/models/insumo";
import { Producto } from "src/app/models/producto";
import { Proyecto } from "src/app/models/proyecto";
import { ProyectoService } from "../proyectos/proyecto.service";
import { BehaviorSubject, Observable, Subject, map } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Usuario } from "src/app/models/usuario";

@Injectable({
  providedIn: "root",
})
export class GestionService {
  ventas: Venta[] = [];
  insumos: Insumo[] = [];
  productos: Producto[] = [];


  // precioVenta: BehaviorSubject<number[]> = new BehaviorSubject([]);
  // fechaVenta: BehaviorSubject<number[]> = new BehaviorSubject([]);
  
  
  ventaMap: Subject<Map<number, object>>=new Subject();
  productoMap: Subject<Map<number, object>>=new Subject();
  insumoMap: Subject<Map<number, object>>=new Subject();
  userData?:Usuario;

  vacio: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null };

  constructor(
    private ventaService: VentaService,
    private insumoService: InsumoService,
    private productoService: ProductoService,
    private proyectoService: ProyectoService,
    private http: HttpClient
  ) {
    ventaService.ventas.subscribe({
      next: (ventasData) => {
        this.ventas = ventasData;
      },
    });

    insumoService.insumos.subscribe({
      next: (insumosData) => {
        this.insumos = insumosData;
      },
    });

    productoService.productos.subscribe({
      next: (productosData) => {
        this.productos = productosData;
      },
    });
  }

  excelUsuario(){
    this.userData=this.proyectoService.getUsuarioData();
    return this.http.get('http://127.0.0.1:8080/api/export/excel-all/'+this.userData.id, {responseType: 'blob'});
  }

  obtenerDatos() {
    this.insumoService.listarInsumos().subscribe();
    this.productoService.listarProductos().subscribe();
    this.ventaService.listarVentas().subscribe();
    setTimeout(() => {
      //this.ventaDatos();
      this.datosVenta();
      this.datosProducto();
      this.datosInsumo();
    }, 200);
  }

  // ventaDatos() {
  //   let precio = [];
  //   this.ventas.forEach((venta) => {
  //     precio.push(venta.precio);
      
  //   });
  //   this.precioVenta.next(precio);
  //  // console.log(precio);
  //   //console.log(this.precioVenta.value);
  //   this.precioVenta.asObservable();

  //   let fecha = [];
  //   this.ventas.forEach((venta) => {
  //     fecha.push(venta.fecha);
  //   });
  //   this.fechaVenta.next(fecha);
  //  // console.log(fecha);
  //   //console.log(this.fechaVenta.value);
  //   this.fechaVenta.asObservable();
  // }

  datosVenta(){
    let ventaPrecioFecha = new Map();
    this.ventas.forEach(venta=>{
      ventaPrecioFecha.set(venta.id, {precio: venta.precio*venta.cantidad, fecha: venta.fecha});
    });
    console.log(ventaPrecioFecha);
    this.ventaMap.next(ventaPrecioFecha);
    this.ventaMap.asObservable();
    return ventaPrecioFecha;
  }

  datosProducto(){
    let productoPrecioFecha = new Map();
    this.productos.forEach(producto=>{
      productoPrecioFecha.set(producto.id, {precio: producto.cantidadInicial*producto.precio, fecha: producto.fecha});
    });
    this.productoMap.next(productoPrecioFecha);
    this.productoMap.asObservable();
    return productoPrecioFecha;
  }

  datosInsumo(){
    let insumoPrecioFecha= new Map();
    this.insumos.forEach(insumo=>{
      insumoPrecioFecha.set(insumo.id, {precio: insumo.precio*insumo.cantidad, fecha: insumo.fecha});
    });
    this.insumoMap.next(insumoPrecioFecha);
    this.productoMap.asObservable();
    return insumoPrecioFecha;
  }


}
