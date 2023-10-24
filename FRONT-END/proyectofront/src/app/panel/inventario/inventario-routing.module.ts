import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductoComponent } from './producto/producto.component';
import { InventarioComponent } from './inventario.component';
import { InsumoComponent } from './insumo/insumo.component';
import { VentaComponent } from './venta/venta.component';

const routes: Routes=[
  {path:'', component:InventarioComponent,
  children:[
    {path:'producto',component:ProductoComponent},
    {path:'insumo', component:InsumoComponent},
    {path:'venta', component:VentaComponent},
    {path:'**', redirectTo:'producto'},
  ]
}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports:[ RouterModule ]
})
export class InventarioRoutingModule { }
