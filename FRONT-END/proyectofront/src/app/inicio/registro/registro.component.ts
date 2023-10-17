import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistroRequest } from 'src/app/models/registroRequest';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  registroError: string = "";
  registroForm=this.formBuilder.group({
    nombre:['', Validators.required],
    email:['',[Validators.required, Validators.email]],
    password:['', [Validators.required, Validators.minLength(5)]],
    confirmPassword:['', [Validators.required, this.confirmPasswordValid()]]
  });
form: any;


  constructor(private formBuilder:FormBuilder, private router:Router, private registroService: LoginService){}

  registro(){
    if(this.registroForm.valid){
        this.registroService.registro(this.registroForm.value as RegistroRequest).subscribe({
          next: (userData) => {
            console.log(userData);
          },
          error: (errorData) => {
            console.error(errorData);
            this.registroForm=errorData;
          },
          complete: () => {
            console.info("logiiiin");
            this.router.navigateByUrl('/panel');
            this.registroForm.reset();
          }
        });
    }else{
      this.registroForm.markAllAsTouched();
    }
  }

  confirmPasswordValid(){
    return (control: FormControl)=>{
      const password = control.parent?.get('password')?.value;
      const confirmpassword = control.value;

      if(password !== confirmpassword){
        console.log("no coincide");
        return{ noCoincide: true};
      }else{
        console.log("coincide");
        return null;
      }
    };
  }

}
