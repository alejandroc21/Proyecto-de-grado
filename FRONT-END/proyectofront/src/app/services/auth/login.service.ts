import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  // login(credencial:LoginRequest):Observable<Usuario>{
  //   return this.http.get<Usuario>('http://127.0.0.1:8080/api/usuarios/1').pipe(
  //     catchError(this.handleError)
  //   );
  // }

  login(credenciales:LoginRequest):Observable<Usuario>{
    return this.http.post<Usuario>('http://127.0.0.1:8080/login', credenciales).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('errores', error.error);
    }else{
      console.error('codigo: ', error.status, error.error);
      return throwError(()=> new Error('datos erroneos'));
    }
    return throwError(()=> new Error('Intente nuevamente'));
  }
}
