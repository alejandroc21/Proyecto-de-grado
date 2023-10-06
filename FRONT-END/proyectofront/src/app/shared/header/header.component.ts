import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/auth/login.service';
import { Usuario } from 'src/app/services/auth/usuario';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  //userLoginOn:boolean=false;
  userData?:Usuario;
  constructor(private loginservice: LoginService){

  }
  
  
  ngOnInit(): void {
    // this.loginservice.currentUserLoginOn.subscribe({
    //   next:(userLoginOn) =>{
    //     this.userLoginOn=userLoginOn;
    //   }
    // });
    this.loginservice.CurrentUserData.subscribe({
      next:(userData)=>{
        this.userData=userData;
      }
    });

    this.prueba();
  }

  prueba(){
    const userDataString = localStorage.getItem('userData');
    if (userDataString) {
      this.userData = JSON.parse(userDataString);
    }
  }
}
