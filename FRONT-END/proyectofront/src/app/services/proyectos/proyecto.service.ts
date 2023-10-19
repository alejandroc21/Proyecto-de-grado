import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/usuario';
import { Proyecto } from 'src/app/models/proyecto';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {
  urlProyectos = 'http://127.0.0.1:8080/api/proyecto';
  userData:Usuario= {id:0, nombre:'', email:''};
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
    return this.http.get<Proyecto[]>(this.urlProyectos+'/listar/'+this.userData?.id);
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

}


