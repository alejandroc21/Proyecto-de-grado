import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ProductoComponent } from "./producto/producto.component";
import { InsumoComponent } from "./insumo/insumo.component";
import { VentaComponent } from "./venta/venta.component";
import { InventarioRoutingModule } from "./inventario-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [ProductoComponent, InsumoComponent, VentaComponent],
  imports: [CommonModule, InventarioRoutingModule,ReactiveFormsModule, FormsModule],
})
export class InventarioModule {}
