import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanelRoutingModule } from './panel-routing.module';
import { PanelIndexComponent } from './panel-index/panel-index.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    PanelIndexComponent
  ],
  imports: [
    CommonModule,
    PanelRoutingModule,
    SharedModule
  ]
})
export class PanelModule { }
