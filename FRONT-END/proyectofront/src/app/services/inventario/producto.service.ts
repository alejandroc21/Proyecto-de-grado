import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ProyectoService } from "../proyectos/proyecto.service";
import { Proyecto } from "src/app/models/proyecto";
import { Producto } from "src/app/models/producto";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Usuario } from "src/app/models/usuario";
import { Venta } from "src/app/models/venta";

@Injectable({
  providedIn: "root",
})
export class ProductoService {
  UrlProducto = "http://127.0.0.1:8080/api/producto";
  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null , categoria:null};
  productos: BehaviorSubject<Producto[]> = new BehaviorSubject<Producto[]>([]);
  userData?:Usuario;
  

  constructor(
    private http: HttpClient,
    private proyectoService: ProyectoService
  ) {
    proyectoService.selected.subscribe({
      next: (data) => {
        this.proyecto = data;
      },
    });
  }

  listarTodoProductos(){
    this.userData=this.proyectoService.getUsuarioData();
    return this.http.get<Producto[]>(this.UrlProducto + "/listar-todo/"+this.userData.id).pipe(
      tap((data: Producto[]) => {
        this.productos.next(data);
      })
    );
  }

  listarProductos() {
    if(this.proyecto.id==0){
      return this.listarTodoProductos();
    }
    return this.http.get<Producto[]>(this.UrlProducto + "/listar/" + this.proyecto.id).pipe(
        tap((data: Producto[]) => {
          this.productos.next(data);
        })
      );
  }

  get lista(): Observable<Producto[]> {
    return this.productos.asObservable();
  }

  crearProducto(producto:Producto){
    producto.proyecto=this.proyecto;
    if(this.proyecto.id==0){
      alert("elige un proyecto");
    }
    return this.http.post<Proyecto>(this.UrlProducto+'/crear', producto, {
      observe: 'response'
    }); 
  }

  ventaMultiple(ventas: Venta[]){
    return this.http.post("http://127.0.0.1:8080/api/venta/crear-multiple", ventas,{
      responseType: 'blob'
    })
  }

  actualizarProducto(producto:Producto){
    producto.proyecto=this.proyecto;
    return this.http.put(this.UrlProducto+'/actualizar', producto, {
      observe: 'response'
    });
  }

  actualizarProductoMultiple(productos:Producto[]){
    productos=productos.map((producto)=>{return{...producto, proyecto: this.proyecto};});
    return this.http.put(this.UrlProducto+'/actualizar-multiple', productos, {
      responseType: 'text'
    });
  }

  eliminarProducto(idProducto:number){
    return this.http.delete(this.UrlProducto+'/eliminar/'+idProducto,{
      responseType: "text"
    });
  }
}
