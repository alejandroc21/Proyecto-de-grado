import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Proyecto } from 'src/app/models/proyecto';
import { Venta } from 'src/app/models/venta';
import { ProyectoService } from '../proyectos/proyecto.service';
import { HttpClient } from '@angular/common/http';
import { Producto } from 'src/app/models/producto';
import { Usuario } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  UrlVenta='http://127.0.0.1:8080/api/venta';
  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  ventas:BehaviorSubject<Venta[]> = new BehaviorSubject<Venta[]>([]);
  userData?:Usuario;

  constructor(private http:HttpClient, private proyectoService:ProyectoService) {
    proyectoService.selected.subscribe({
      next:(data)=>{
        this.proyecto=data;
      }
    });
   }

   listarTodoVentas(){
    this.userData=this.proyectoService.getUsuarioData();
    return this.http.get<Venta[]>(this.UrlVenta+'/listar-todo/'+this.userData.id).pipe(
      tap((data:Venta[])=>{
        this.ventas.next(data)
      }));
   }

   listarVentas(){
    if(this.proyecto.id==0){
      return this.listarTodoVentas();
    }
    return this.http.get<Venta[]>(this.UrlVenta+'/listar/'+this.proyecto.id).pipe(
      tap((data:Venta[])=>{
        this.ventas.next(data)
      })
    );
  }

  get lista():Observable<Venta[]>{
    return this.ventas.asObservable();
  }

  crearVenta(venta: Venta){
    venta.proyecto=this.proyecto;
    return this.http.post<Proyecto>(this.UrlVenta+'/crear', venta, {
      observe: 'response'
    });
  }

  actualizarVenta(venta: Venta){
    venta.proyecto=this.proyecto;
    return this.http.put<Proyecto>(this.UrlVenta+'/actualizar', venta, {
      observe: 'response'
    });
  }

  eliminarVenta(idVenta:number){
    return this.http.delete(this.UrlVenta+'/eliminar/'+idVenta,{
      responseType: "text"
    });
  }
}
