import { Component, OnDestroy, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { ProyectoService } from 'src/app/services/proyectos/proyecto.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  //userLoginOn:boolean=false;
  userData?:Usuario;
  constructor(private proyectoService: ProyectoService){

  }
  
  
  ngOnInit(): void {
    // this.loginservice.currentUserLoginOn.subscribe({
    //   next:(userLoginOn) =>{
    //     this.userLoginOn=userLoginOn;
    //   }
    // });
    // this.loginservice.CurrentUserData.subscribe({
    //   next:(userData)=>{
    //     this.userData=userData;
    //   }
    // });

    this.userData=this.proyectoService.getUsuarioData();
  }

}
