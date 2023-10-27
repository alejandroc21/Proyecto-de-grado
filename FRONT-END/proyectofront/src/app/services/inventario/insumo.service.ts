import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProyectoService } from '../proyectos/proyecto.service';
import { Insumo } from 'src/app/models/insumo';
import { Proyecto } from 'src/app/models/proyecto';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InsumoService {

  UrlInsumo='http://127.0.0.1:8080/api/insumo';
  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  insumos:BehaviorSubject<Insumo[]>=new BehaviorSubject<Insumo[]>([]);
  
  constructor(private http:HttpClient, private proyectoService:ProyectoService) {
    proyectoService.selected.subscribe({
      next:(data)=>{
        this.proyecto=data;
      }
    });
   }

  listarInsumos(){
    return this.http.get<Insumo[]>(this.UrlInsumo+'/listar/'+this.proyecto.id).pipe(
      tap((data:Insumo[])=>{
        this.insumos.next(data)
      })
    );
  }

  get lista():Observable<Insumo[]>{
    return this.insumos.asObservable();
  }

  crearInsumo(insumo:Insumo){
    insumo.proyecto=this.proyecto;
    return this.http.post<Proyecto>(this.UrlInsumo+'/crear', insumo, {
      observe: 'response'
    });
  }

  actualizarInsumo(insumo:Insumo){
    insumo.proyecto=this.proyecto;
    return this.http.put<Proyecto>(this.UrlInsumo+'/actualizar', insumo, {
      observe: 'response'
    });
  }

  eliminarInsumo(idInsumo:number){
    return this.http.delete(this.UrlInsumo+'/eliminar/'+idInsumo,{
      responseType: "text"
    });
  }
}
