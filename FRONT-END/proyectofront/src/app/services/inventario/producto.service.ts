import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ProyectoService } from "../proyectos/proyecto.service";
import { Proyecto } from "src/app/models/proyecto";
import { Producto } from "src/app/models/producto";
import { BehaviorSubject, Observable, tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ProductoService {
  UrlProducto = "http://127.0.0.1:8080/api/producto";
  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null };
  productos: BehaviorSubject<Producto[]> = new BehaviorSubject<Producto[]>([]);

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

  listarProductos() {
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
    return this.http.post<Proyecto>(this.UrlProducto+'/crear', producto, {
      observe: 'response'
    }); 
  }

  actualizarProducto(producto:Producto){
    producto.proyecto=this.proyecto;
    return this.http.put<Proyecto>(this.UrlProducto+'/actualizar', producto, {
      observe: 'response'
    });
  }

  eliminarProducto(idProducto:number){
    return this.http.delete(this.UrlProducto+'/eliminar/'+idProducto,{
      responseType: "text"
    });
  }
}
