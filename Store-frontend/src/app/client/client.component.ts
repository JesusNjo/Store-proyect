import { Component, OnInit, inject } from '@angular/core';
import { Client } from '../models/client';
import { ClientService } from '../service/client/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {


  clientList:Client[] = [];
  private clientService = inject(ClientService);
  ngOnInit(): void {
    this.clientService.findAllClient().subscribe({
      next: (clients:Client[])=>{
        this.clientList = clients;
      },
      error: (error:Error)=>{
        return error;
      }
    })
  }

}
