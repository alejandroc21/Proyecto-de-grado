import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/auth/login.service';
import { LoginRequest } from 'src/app/services/auth/loginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  loginForm=this.formBuilder.group({
    email:['alex@gmail.com',[Validators.required, Validators.email]],
    password:['', Validators.required],
  })
  constructor(private formBuilder:FormBuilder, private router:Router, private loginService: LoginService){}

  login(){
    if(this.loginForm.valid){
      this.loginService.login(this.loginForm.value as LoginRequest);
      this.router.navigateByUrl('/panel');
      this.loginForm.reset();
    }else{
      this.loginForm.markAllAsTouched();
    }
  }
}
