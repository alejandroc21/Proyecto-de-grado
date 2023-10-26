import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Proyecto } from 'src/app/models/proyecto';
import { ProyectoService } from 'src/app/services/proyectos/proyecto.service';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent implements OnInit{

  proyectos: Proyecto[]=[];
  selectProyecto: Proyecto = {id:0,nombre:'',descripcion:'',usuario:null};
  editar:Boolean=false;
  proyectoForm=this.formBuilder.group({
    nombre: ['', Validators.required],
    descripcion: ['', Validators.required]
  });
  
  constructor(private proyectoService: ProyectoService, private formBuilder : FormBuilder){}

  ngOnInit(): void {
    this.listar();
  }

  listar(){
    this.proyectoService.listarProyectos().subscribe((data)=>this.proyectos=data);
  }

  //crear o editar usando el mismo formulario
  botonProyecto(){
    if(this.selectProyecto.id===0){
      this.crearProyecto();
    }else{
      this.actualizarProyecto();
      console.log("actualiza");
    }
  }

  crearProyecto(){
      if(this.proyectoForm.valid){
        this.proyectoService.crearProyecto(this.proyectoForm.value as Proyecto).subscribe(res =>{
          this.proyectoForm.reset();
          this.listar();
          
        });
      }else{
        this.proyectoForm.markAllAsTouched();
      }
  }

  actualizarProyecto(){
    if(this.proyectoForm.valid){
      this.selectProyecto.nombre=this.proyectoForm.get('nombre')?.value as string;
      this.selectProyecto.descripcion=this.proyectoForm.get('descripcion')?.value as string;
      this.proyectoService.actualizarProyecto(this.selectProyecto).subscribe(res=>{
        this.proyectoForm.reset();
        this.listar();
        this.selectProyecto = {id:0,nombre:'',descripcion:'',usuario:null};
      });
    }else{
      this.proyectoForm.markAllAsTouched();
    }
  }

  //guarda el proyecto seleccionado para ser editado en selectProyecto
  //si se clickea nuevamente lo deja en blanco
  selectEditProyecto(proyecto:Proyecto){
    if(proyecto!==this.selectProyecto||!this.editar){
      this.editar=true;
      this.selectProyecto=proyecto;
      this.proyectoForm.setValue({
        nombre: this.selectProyecto.nombre,
        descripcion:this.selectProyecto.descripcion
      });
    }else{
      this.editar=false;
      this.selectProyecto = {id:0,nombre:'',descripcion:'',usuario:null};
      this.proyectoForm.setValue({
        nombre: '',
        descripcion:''
      });
    }
  }

  eliminarProyecto(idProyecto:number){
      this.proyectoService.borrarProyecto(idProyecto).subscribe(res =>{
        this.listar();
      });
  }

}
