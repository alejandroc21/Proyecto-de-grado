import { Component, OnInit } from '@angular/core';
import { ProyectoService } from 'src/app/services/proyectos/proyecto.service';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent implements OnInit{

  proyectos: any[]=[];

  constructor(private proyectoService: ProyectoService){}

  ngOnInit(): void {
    this.proyectoService.listarProyectos().subscribe((data: any)=>this.proyectos=data);
  }

}
