import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Producto } from 'src/app/models/producto';
import { Proyecto } from 'src/app/models/proyecto';
import { Venta } from 'src/app/models/venta';
import { ProductoService } from 'src/app/services/inventario/producto.service';
import { VentaService } from 'src/app/services/inventario/venta.service';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit{
  ventas:Venta[]=[];
  productos:Producto[]=[];

  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  venta:Venta={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto};
  editar:Boolean=false;

  ventaForm=this.formBuilder.group({
    nombre:['',Validators.required],
    precio:['',Validators.required],
    cantidad:['',Validators.required],
    fecha:['',Validators.required]
  });

  constructor(private ventaService:VentaService, private formBuilder:FormBuilder, private productoService:ProductoService){}

  ngOnInit(): void {
    this.ventaService.ventas.subscribe((data)=>this.ventas=data);
    this.productoService.listarProductos().subscribe((data)=>this.productos=data);
    this.productoService.productos.subscribe((data)=>this.productos=data);
    this.listarVentas();
  }

  listarVentas(){
    this.ventaService.listarVentas().subscribe((data)=>this.ventas=data);
  }

  botonVenta(){
    if(this.venta.id===0){
      this.crearVenta();
    }else{
      this.actualizarVenta();
    }
  }

  crearVenta(){
    if(this.ventaForm.valid){
      this.venta.nombre=this.ventaForm.value.nombre!;
      this.venta.precio=parseFloat(this.ventaForm.value.precio!);
      this.venta.cantidad=parseInt(this.ventaForm.value.cantidad!);
      this.venta.fecha=new Date(this.ventaForm.value.fecha!);
      this.ventaService.crearVenta(this.venta).subscribe(res=>{
        this.ventaForm.reset();
        this.listarVentas();
      });
    }else{
      this.ventaForm.markAllAsTouched();
    }
  }

  actualizarVenta(){
    if(this.ventaForm.valid){
      this.venta.nombre=this.ventaForm.value.nombre!;
      this.venta.precio=parseFloat(this.ventaForm.value.precio!);
      this.venta.cantidad=parseInt(this.ventaForm.value.cantidad!);
      this.venta.fecha=new Date(this.ventaForm.value.fecha!);
      this.ventaService.actualizarVenta(this.venta).subscribe(res=>{
        this.ventaForm.reset();
        this.listarVentas();
        this.venta={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto};
      });
    }else{
      this.ventaForm.markAllAsTouched();
    }
  }

  selectEdithVenta(venta:Venta){
    if(venta!==this.venta||!this.editar){
      this.editar=true;
      this.venta=venta;

      this.ventaForm.setValue({
        nombre: this.venta.nombre,
        precio: this.venta.precio.toString(),
        cantidad: this.venta.cantidad.toString(),
        fecha: this.venta.fecha.toString()
      });
    }else{
      this.editar=false;
      venta={id:0,nombre:'',precio:0,cantidad:0,fecha:new Date(''),proyecto:this.proyecto};
      this.ventaForm.setValue({
        nombre: '',
        precio: '',
        cantidad: '',
        fecha: ''
      });
    }
  }

  eliminarVenta(IdVenta:number){
    this.ventaService.eliminarVenta(IdVenta).subscribe(res=>{
      this.listarVentas();
    });
  }
}
