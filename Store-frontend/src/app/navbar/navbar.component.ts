import { Component, OnInit, inject } from '@angular/core';
import { LoginService } from '../service/client/auth.service';
import { Client } from '../models/client';
import { ClientService } from '../service/client/client.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  clientLoginOn:boolean = false;
  private authService = inject(LoginService)
  private clientService = inject(ClientService);
  private router = inject(ActivatedRoute);
  clientData:Client|any;
  client?:Client;

  ngOnInit(): void {
    this.authService.currentClientLoginOn.subscribe({
      next:(userLoginOn)=>{
        this.clientLoginOn = userLoginOn;
      }
    })
    this.authService.currentClientData.subscribe({
      next:(client:Client)=>{
       this.clientData = client;
      }
    })

    this.router.params.subscribe(params=>{
      this.clientService.findClientByEmail(params[this.clientData?.email]).subscribe({
        next: (client:Client)=>{
          this.client = client;
        }
      })
    })

    
  }




}
