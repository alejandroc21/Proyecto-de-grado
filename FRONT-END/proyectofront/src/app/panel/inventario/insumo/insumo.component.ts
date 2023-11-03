import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ModalService } from 'src/app/modal/modal.service';
import { Insumo } from 'src/app/models/insumo';
import { Proyecto } from 'src/app/models/proyecto';
import { InsumoService } from 'src/app/services/inventario/insumo.service';

@Component({
  selector: 'app-insumo',
  templateUrl: './insumo.component.html',
  styleUrls: ['./insumo.component.css']
})
export class InsumoComponent implements OnInit{

  insumos:Insumo[]=[];
  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  insumo:Insumo={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto}
  selectedInsumo:Insumo={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto}
  editar:Boolean=false;

  insumoForm=this.formBuilder.group({
    nombre:['',Validators.required],
    precio:['',Validators.required],
    cantidad:['',Validators.required],
    fecha:['',Validators.required]
  });

  constructor(private insumoService:InsumoService, private formBuilder: FormBuilder){
    
  }
  ngOnInit(): void {
    
    this.insumoService.insumos.subscribe((data)=>this.insumos=data);
    this.listarInsumos();
  }


  listarInsumos(){
    this.insumoService.listarInsumos().subscribe((data)=>this.insumos=data);
    
  }

  botonInsumo(){
    if(this.selectedInsumo.id===0){
      this.crearInsumo();
    }else{
      this.actualizarInsumo();
    }
  }

  crearInsumo(){
    if(this.insumoForm.valid){
      this.insumo.nombre=this.insumoForm.value.nombre!;
      this.insumo.cantidad=parseInt(this.insumoForm.value.cantidad!);
      this.insumo.precio=parseFloat(this.insumoForm.value.precio!);
      //this.insumo.fecha=new Date(this.insumoForm.value.fecha!);

      let formFecha = this.insumoForm.value.fecha;
      let reparar= new Date(formFecha);
      this.insumo.fecha=new Date (reparar.getTime()+reparar.getTimezoneOffset()*60000);

      this.insumoService.crearInsumo(this.insumo).subscribe(res=>{
        this.insumoForm.reset();
        this.listarInsumos();
      });
    }else{
      this.insumoForm.markAllAsTouched();
    }
  }

  actualizarInsumo(){
    if(this.insumoForm.valid){
      this.selectedInsumo.nombre=this.insumoForm.value.nombre!;
      this.selectedInsumo.cantidad=parseInt(this.insumoForm.value.cantidad!);
      this.selectedInsumo.precio=parseFloat(this.insumoForm.value.precio!);
      //this.selectedInsumo.fecha=new Date(this.insumoForm.value.fecha!);

      let formFecha = this.insumoForm.value.fecha;
      let reparar= new Date(formFecha);
      this.insumo.fecha=new Date (reparar.getTime()+reparar.getTimezoneOffset()*60000);

      this.insumoService.actualizarInsumo(this.selectedInsumo).subscribe(res=>{
        this.insumoForm.reset();
        this.listarInsumos();
        this.selectedInsumo={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto};
      });
    }else{
      this.insumoForm.markAllAsTouched();
    }
  }

  selectEdithInsumo(insumo:Insumo){
    if(insumo!==this.selectedInsumo || !this.editar){
      this.editar=true;
      this.selectedInsumo=insumo;

      this.insumoForm.setValue({
        nombre: this.selectedInsumo.nombre,
        precio: this.selectedInsumo.precio.toString(),
        cantidad: this.selectedInsumo.cantidad.toString(),
        fecha: this.selectedInsumo.fecha.toString()
      });
    }else{
      this.editar=false;
      this.selectedInsumo={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto};
      this.insumoForm.setValue({
        nombre: '',
        precio: '',
        cantidad: '',
        fecha: ''
      });
    }
  }

  eliminarInsumo(idInsumo:number){
    this.insumoService.eliminarInsumo(idInsumo).subscribe(res=>{
      this.listarInsumos();
    });
  }
}
