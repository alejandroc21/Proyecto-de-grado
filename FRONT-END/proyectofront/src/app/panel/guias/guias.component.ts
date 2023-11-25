import { Component, OnInit } from '@angular/core';
import { Proyecto } from 'src/app/models/proyecto';
import { ProyectoService } from 'src/app/services/proyectos/proyecto.service';

@Component({
  selector: 'app-guias',
  templateUrl: './guias.component.html',
  styleUrls: ['./guias.component.css']
})
export class GuiasComponent implements OnInit{

  proyecto: Proyecto = {id:0,nombre:'',descripcion:'',usuario:null, categoria:null};

  constructor(private proyectoService: ProyectoService){

  }

  ngOnInit(): void {
    this.proyectoService.getSelected$().subscribe(selected=> {
      if(selected.id!==0){
        this.proyecto=selected;
      }
    });
  }
}
