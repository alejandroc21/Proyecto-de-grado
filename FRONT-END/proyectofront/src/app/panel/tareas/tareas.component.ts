import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Proyecto } from 'src/app/models/proyecto';
import { Tarea } from 'src/app/models/tarea';
import { TareasService } from 'src/app/services/tareas/tareas.service';

@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.component.html',
  styleUrls: ['./tareas.component.css']
})
export class TareasComponent implements OnInit{

  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null , categoria:null};
  tarea:Tarea={id:0, tarea:'', realizado:false, proyecto:this.proyecto}
  tareas:Tarea[]=[];

  tareaForm=this.formBuilder.group({
    tarea:["", Validators.required]
  })

  constructor(private tareaService:TareasService, private formBuilder: FormBuilder){}


  ngOnInit(): void {
    this.listarTareas();
  }

  listarTareas(){
    this.tareaService.tareas.subscribe((data)=>this.tareas=data);
    this.tareaService.listarTareas().subscribe((data)=>this.tareas=data);
  }

  crearTarea(){
    if(this.tareaForm.valid){
      this.tarea.tarea=this.tareaForm.value.tarea;
      this.tareaService.crearTarea(this.tarea).subscribe(res=>{
        this.tareaForm.reset();
        this.listarTareas();
      });
    }else{
      this.tareaForm.markAllAsTouched();
    }
    
  }

  actualizarTarea(tarea:Tarea){
    this.tarea.id=tarea.id;
    this.tarea.tarea=tarea.tarea;  
    this.tarea.realizado=tarea.realizado;
    
    
    this.tareaService.actualizarTarea(this.tarea).subscribe(res=>{
      this.listarTareas();
    });
  }

  eliminarTarea(idTarea:number){
    this.tareaService.EliminarTarea(idTarea).subscribe(res=>{
      this.listarTareas();
    });
  }
}
