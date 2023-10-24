import { Component, OnInit } from '@angular/core';
import { Venta } from 'src/app/models/venta';
import { VentaService } from 'src/app/services/inventario/venta.service';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit{
  ventas:Venta[]=[];

  constructor(private ventaService:VentaService){}

  ngOnInit(): void {
    this.ventaService.ventas.subscribe((data)=>this.ventas=data);
    this.listarVentas();
  }

  listarVentas(){
    this.ventaService.listarVentas().subscribe((data)=>this.ventas=data);
  }
}
