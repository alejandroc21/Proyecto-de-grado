import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Proyecto } from 'src/app/models/proyecto';
import { ProyectoService } from '../proyectos/proyecto.service';
import { Cliente } from 'src/app/models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  UrlCliente = "http://127.0.0.1:8080/api/cliente";
  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null , categoria:null};
  
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

  listarClientes(){
    return this.http.get<Cliente[]>(this.UrlCliente+'/listar/'+this.proyecto.id, {
      observe: 'response'
    });
  }

  crearCliente(cliente:Cliente){
    return this.http.post<Cliente>(this.UrlCliente+'/crear',cliente,{
      observe: 'response'
    })
  }
}
