import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tarea } from 'src/app/models/tarea';
import { ProyectoService } from '../proyectos/proyecto.service';
import { Proyecto } from 'src/app/models/proyecto';
import { BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TareasService {
  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null , categoria:null};
  UlrTareas='http://127.0.0.1:8080/api/tarea';
  tareas: BehaviorSubject<Tarea[]>=new BehaviorSubject<Tarea[]>([]);

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

  listarTareas(){
    return this.http.get<Tarea[]>(this.UlrTareas+'/listar/'+this.proyecto.id).pipe(
      tap((data: Tarea[])=>{
        this.tareas.next(data);
      })
    );
  }

  crearTarea(tarea:Tarea){
    if(this.proyecto.id==0){
      alert("elige un proyecto");
    }
    tarea.proyecto=this.proyecto;
    return this.http.post<Tarea>(this.UlrTareas+'/crear',tarea,{
      observe: 'response'
    });
  }

  actualizarTarea(tarea:Tarea){
    if(this.proyecto.id==0){
      alert("elige un proyecto");
    }
    tarea.proyecto=this.proyecto;
    return this.http.put<Tarea>(this.UlrTareas+'/actualizar',tarea,{
      observe:'response'
    });
  }

  EliminarTarea(idTarea:number){
    return this.http.delete(this.UlrTareas+'/eliminar/'+idTarea,{
      responseType:'text'
    });
  }
}
