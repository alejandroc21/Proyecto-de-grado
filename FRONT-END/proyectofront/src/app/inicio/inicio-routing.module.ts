import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioIndexComponent } from './inicio-index/inicio-index.component';
import { LoginComponent } from './login/login.component';
import { PresentacionComponent } from './presentacion/presentacion.component';
import { RegistroComponent } from './registro/registro.component';

const routes: Routes = [
  {
    path: '',
    component: InicioIndexComponent,
    children: [
      {path: '', component: PresentacionComponent},
      {path: 'login', component: LoginComponent},
      {path: 'registro', component: RegistroComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InicioRoutingModule { }
