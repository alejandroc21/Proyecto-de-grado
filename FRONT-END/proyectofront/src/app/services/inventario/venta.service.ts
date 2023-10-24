import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Proyecto } from 'src/app/models/proyecto';
import { Venta } from 'src/app/models/venta';
import { ProyectoService } from '../proyectos/proyecto.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  UrlVenta='http://127.0.0.1:8080/api/venta';
  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  ventas:BehaviorSubject<Venta[]> = new BehaviorSubject<Venta[]>([]);

  constructor(private http:HttpClient, private proyectoService:ProyectoService) {
    proyectoService.selected.subscribe({
      next:(data)=>{
        this.proyecto=data;
      }
    });
   }

   listarVentas(){
    return this.http.get<Venta[]>(this.UrlVenta+'/listar/'+this.proyecto.id).pipe(
      tap((data:Venta[])=>{
        this.ventas.next(data)
      })
    );
  }

  get lista():Observable<Venta[]>{
    return this.ventas.asObservable();
  }
}
