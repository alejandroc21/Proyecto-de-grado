import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/models/producto';
import { ProductoService } from 'src/app/services/inventario/producto.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit{

  productos:Producto[]=[];

  constructor(private productoService:ProductoService){}

  ngOnInit(): void {
    this.productoService.productos.subscribe((data)=>this.productos=data);
    this.listarProductos();
  }

  listarProductos(){
    this.productoService.listarProductos().subscribe((data)=>this.productos=data);
  }
}
