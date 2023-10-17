import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {
  urlProyectos = 'http://127.0.0.1:8080/api/proyecto';
  userData?:Usuario;
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
    return this.http.get(this.urlProyectos+'/listar/1');
  }

}
