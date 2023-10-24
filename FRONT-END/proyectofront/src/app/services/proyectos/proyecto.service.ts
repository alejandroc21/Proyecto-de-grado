import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/usuario';
import { Proyecto } from 'src/app/models/proyecto';
import { BehaviorSubject, Observable, Subject, map, mergeMap, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {
  urlProyectos = 'http://127.0.0.1:8080/api/proyecto';
  userData:Usuario= {id:0, nombre:'', email:''};
  //private proyectos:Proyecto[]=[];
  selected:BehaviorSubject <Proyecto>=new BehaviorSubject<Proyecto>({id:0,nombre:'',descripcion:'',usuario:null});
  proyectosObs: Subject<Proyecto[]>=new Subject();

  constructor(private http: HttpClient) { }

  //Obtener los ususarios guardados en localStorage
  getUsuarioData(){
    const userDataString = localStorage.getItem('userData');
    if (userDataString) {
      this.userData = JSON.parse(userDataString);
      console.log(this.userData);
    }
    return this.userData;
  }

  //Listar los proyectos del usuario
  listarProyectos(){
    return this.http.get<Proyecto[]>(this.urlProyectos+'/listar/'+this.userData?.id).pipe(
      tap((proyectos:Proyecto[])=>{
        this.proyectosObs.next(proyectos);
      })
    );
  }

  get listaProyectos():Observable<Proyecto[]>{
    return this.proyectosObs.asObservable();
  }

  crearProyecto(proyecto:Proyecto){
      proyecto.usuario=this.userData;
      return this.http.post<Proyecto>(this.urlProyectos+'/crear', proyecto, {
        observe: 'response'
      });
  }

  actualizarProyecto(proyecto: Proyecto){
      proyecto.usuario=this.userData;
      return this.http.put<Proyecto>(this.urlProyectos+'/actualizar', proyecto, {
        observe: 'response'
      });
  }

  borrarProyecto(idProyecto: number){
      return this.http.delete(this.urlProyectos+'/eliminar/'+idProyecto,{
        responseType: "text"
      });
  }

  elegido(proyecto:Proyecto){
    this.selected.next(proyecto);
  }


}


