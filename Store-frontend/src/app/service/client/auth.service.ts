import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, throwError, BehaviorSubject, tap } from 'rxjs';
import { Client } from 'src/app/models/client';
import { ClientLoginRequest } from 'src/app/models/clientRequest';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentClientLoginOn:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentClientData: BehaviorSubject<Client> = new BehaviorSubject<Client>({id:"",
    name:"",
    username:"",
    lastName:"",
    email:"",
    address:"",
    password:""});

  urlBase:string = "http://localhost:8080/auth"
  private httpClient = inject(HttpClient);

  constructor() { }


  public login = (client:ClientLoginRequest):Observable<Client>=>{
    console.log(client);
    
    return this.httpClient.post<Client>(`${this.urlBase}/login`,client).pipe(
      tap(dataClient => {
        this.currentClientData.next(dataClient);
        this.currentClientLoginOn.next(true);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(res:HttpErrorResponse){
    if(res.status===0){
      console.log(`Error producido : ${res.error}`);
    }else{
      console.log(`Codigo de estado: ${res.status}`);
    }
    return throwError(()=> new Error('Algo fallo en la ejecución'));
  }

  get userData():Observable<Client>{
    return this.currentClientData.asObservable(); 
  }
  get userLoginOn():Observable<boolean>{
    return this.currentClientLoginOn.asObservable();
  }
  
}