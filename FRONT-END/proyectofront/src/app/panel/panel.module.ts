import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanelRoutingModule } from './panel-routing.module';
import { PanelIndexComponent } from './panel-index/panel-index.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProyectosComponent } from './proyectos/proyectos.component';
import { GestionComponent } from './gestion/gestion.component';
import { InventarioComponent } from './inventario/inventario.component';
import { ProductoComponent } from './inventario/producto/producto.component';
import { InsumoComponent } from './inventario/insumo/insumo.component';
import { VentaComponent } from './inventario/venta/venta.component';


@NgModule({
  declarations: [
    PanelIndexComponent,
    ProyectosComponent,
    GestionComponent,
    InventarioComponent,
    
  ],
  imports: [
    CommonModule,
    PanelRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class PanelModule { }
