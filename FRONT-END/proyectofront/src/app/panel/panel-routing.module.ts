import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanelIndexComponent } from './panel-index/panel-index.component';
import { ProyectosComponent } from './proyectos/proyectos.component';
import { GestionComponent } from './gestion/gestion.component';
import { InventarioComponent } from './inventario/inventario.component';
import { TareasComponent } from './tareas/tareas.component';
import { GuiasComponent } from './guias/guias.component';

const routes: Routes = [
  {
    path: '', component: PanelIndexComponent,
    children: [
      {path: 'proyectos', component: ProyectosComponent},
      {path: 'gestion', component: GestionComponent},
      {path: 'inventario', loadChildren: ()=>import('./inventario/inventario.module').then(m=>m.InventarioModule)},
      {path: 'tareas', component:TareasComponent},
      {path: 'guias', component: GuiasComponent},
      {path: '**', redirectTo:'proyectos'}
    ]
  },
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PanelRoutingModule { }
