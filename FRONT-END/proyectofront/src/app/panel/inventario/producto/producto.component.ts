import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Producto } from 'src/app/models/producto';
import { Proyecto } from 'src/app/models/proyecto';
import { ProductoService } from 'src/app/services/inventario/producto.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit{

  productos:Producto[]=[];
  proyecto:Proyecto={id:0,nombre:'',descripcion:'',usuario:null};
  producto:Producto={id:0,nombre:'',descripcion:'',cantidadInicial:0,cantidadFinal:0,precio:0,fecha:new Date(''),proyecto:this.proyecto};
  selectedProducto:Producto={id:0,nombre:'',descripcion:'',cantidadInicial:0,cantidadFinal:0,precio:0,fecha:new Date(''),proyecto:this.proyecto};
  editar:Boolean=false;

  productoForm=this.formBuilder.group({
    nombre:['', Validators.required],
    descripcion:[''],
    cantidadFinal:['',Validators.required],
    precio:['',Validators.required],
    fecha:['',Validators.required]
  });

  constructor(private productoService:ProductoService,private formBuilder:FormBuilder){}

  ngOnInit(): void {
    this.productoService.productos.subscribe((data)=>this.productos=data);
    this.listarProductos();
  }

  listarProductos(){
    this.productoService.listarProductos().subscribe((data)=>this.productos=data);
  }

  botonProducto(){
    if(this.selectedProducto.id===0){
      this.crearProducto();
    }else{
      this.actualizarProducto();
    }
  }

  crearProducto(){
    if(this.productoForm.valid){
      this.producto.nombre=this.productoForm.value.nombre!;
      this.producto.descripcion=this.productoForm.value.descripcion!;
      this.producto.cantidadInicial=parseInt(this.productoForm.value.cantidadFinal!);
      this.producto.cantidadFinal=parseInt(this.productoForm.value.cantidadFinal!);
      this.producto.precio=parseFloat(this.productoForm.value.precio!);
      //this.producto.fecha=new Date(this.productoForm.value.fecha!);

      let formFecha=this.productoForm.value.fecha;
      let reparar= new Date(formFecha);
      this.producto.fecha=new Date (reparar.getTime()+reparar.getTimezoneOffset()*60000);

      this.productoService.crearProducto(this.producto).subscribe(res=>{
        this.productoForm.reset();
        this.listarProductos();
      });
    }else{
      this.productoForm.markAllAsTouched();
    }
    
  }

  actualizarProducto(){
    if(this.productoForm.valid){
      this.selectedProducto.nombre=this.productoForm.value.nombre!;
      this.selectedProducto.descripcion=this.productoForm.value.descripcion!;
      this.selectedProducto.precio=parseFloat(this.productoForm.value.precio!);
      this.selectedProducto.cantidadInicial=parseInt(this.productoForm.value.cantidadFinal!);
      this.selectedProducto.cantidadFinal=parseInt(this.productoForm.value.cantidadFinal!);
      //this.selectedProducto.fecha=new Date(this.productoForm.value.fecha!);

      let formFecha=this.productoForm.value.fecha;
      let reparar= new Date(formFecha);
      this.producto.fecha=new Date (reparar.getTime()+reparar.getTimezoneOffset()*60000);

      this.productoService.actualizarProducto(this.selectedProducto).subscribe(res=>{
        this.productoForm.reset();
        this.listarProductos();
        this.selectedProducto={id:0,nombre:'',descripcion:'',cantidadInicial:0,cantidadFinal:0,precio:0,fecha:new Date(''),proyecto:this.proyecto};
      })
    }else{
      this.productoForm.markAllAsTouched();
    }
  }

  selectEdithProducto(producto:Producto){
    if(producto!==this.selectedProducto || !this.editar){
      this.editar=true;
      this.selectedProducto=producto;
      this.productoForm.setValue({
        nombre: this.selectedProducto.nombre,
        descripcion: this.selectedProducto.descripcion,
        precio: this.selectedProducto.precio.toString(),
        cantidadFinal: this.selectedProducto.cantidadFinal.toString(),
        fecha:this.selectedProducto.fecha.toString()
      });
    }else{
      this.editar=false;
      this.selectedProducto={id:0,nombre:'',descripcion:'',cantidadInicial:0,cantidadFinal:0,precio:0,fecha:new Date(''),proyecto:this.proyecto};
      this.productoForm.setValue({
        nombre: '',
        descripcion: '',
        precio: '',
        cantidadFinal: '',
        fecha:''
      });
    }
  }

  eliminarProducto(idProducto:number){
    this.productoService.eliminarProducto(idProducto).subscribe(res=>{
      this.listarProductos();
    });
  }
}
