import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PanelIndexComponent } from './panel-index/panel-index.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {path: '', component: PanelIndexComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PanelRoutingModule { }
