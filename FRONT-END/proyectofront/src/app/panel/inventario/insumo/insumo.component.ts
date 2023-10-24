import { Component, OnInit } from '@angular/core';
import { Insumo } from 'src/app/models/insumo';
import { InsumoService } from 'src/app/services/inventario/insumo.service';

@Component({
  selector: 'app-insumo',
  templateUrl: './insumo.component.html',
  styleUrls: ['./insumo.component.css']
})
export class InsumoComponent implements OnInit{

  insumos:Insumo[]=[];
  constructor(private insumoService:InsumoService){
    
  }
  ngOnInit(): void {
    this.insumoService.insumos.subscribe((data)=>this.insumos=data);
   this.listarInsumos(); 
  }

  listarInsumos(){
    this.insumoService.listarInsumos().subscribe((data)=>this.insumos=data);
  }
}
