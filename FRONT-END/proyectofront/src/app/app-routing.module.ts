import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
 {
  path: '',
  loadChildren: () => import('./inicio/inicio.module').then(m => m.InicioModule)
 },
 {
  path: 'panel',
  loadChildren: () => import('./panel/panel.module').then(m => m.PanelModule)
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
