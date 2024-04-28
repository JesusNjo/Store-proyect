import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/models/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  url:string= "http://localhost:8080/client"
  private httpClient = inject(HttpClient);

  constructor() { }


  public findAllClient=():Observable<Client[]>=>{
    return this.httpClient.get<Client[]>(`${this.url}`);
  }
  public findClientById=(id:string):Observable<Client>=>{
    return this.httpClient.get<Client>(`${this.url}/${id}`);
  }
  public createClient=(client:Client):Observable<Client>=>{
    return this.httpClient.post<Client>(`${this.url}`,client)
  }
}
