import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InicioRoutingModule } from './inicio-routing.module';
import { InicioIndexComponent } from './inicio-index/inicio-index.component';
import { LoginComponent } from './login/login.component';
import { PresentacionComponent } from './presentacion/presentacion.component';
import { RegistroComponent } from './registro/registro.component';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    InicioIndexComponent, LoginComponent, PresentacionComponent, RegistroComponent
  ],
  imports: [
    CommonModule,
    InicioRoutingModule,
    SharedModule
  ]
})
export class InicioModule { }
