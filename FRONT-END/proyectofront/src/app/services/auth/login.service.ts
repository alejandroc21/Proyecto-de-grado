import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError, BehaviorSubject, tap } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  CurrentUserData: BehaviorSubject<Usuario> = new BehaviorSubject<Usuario>({id:0, nombre:"", email:""});

  constructor(private http: HttpClient) { }

  login(credencial:LoginRequest):Observable<Usuario>{
    return this.http.get<Usuario>('././assets/data.json').pipe(
      tap((userData: Usuario)=>{
        this.CurrentUserData.next(userData);
      //  this.currentUserLoginOn.next(true);
        localStorage.setItem("userData",JSON.stringify(userData));
      }),
      catchError(this.handleError)
    );
  }

  // login(credenciales:LoginRequest):Observable<Usuario>{
  //   return this.http.post<Usuario>('http://127.0.0.1:8080/login', credenciales).pipe(
  //     catchError(this.handleError)
  //   );
  // }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('errores', error.error);
    }else{
      console.error('codigo: ', error.status, error.error);
      return throwError(()=> new Error('datos erroneos'));
    }
    return throwError(()=> new Error('Intente nuevamente'));
  }

  // get userData():Observable<Usuario>{
  //   return this.CurrentUserData.asObservable();
  // }

  // get UserLoginOn():Observable<boolean>{
  //   return this.currentUserLoginOn.asObservable();
  // }

  
}
