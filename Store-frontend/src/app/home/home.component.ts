import { Component, OnInit, inject } from '@angular/core';
import { Product } from '../models/product';
import { ProductService } from '../service/product/product.service';
import { LoginService } from '../service/client/auth.service';
import { Client } from '../models/client';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products?: Product[];
  client?:Client;

  private productService = inject(ProductService);
  private authService = inject(LoginService);

  showCategorySelector: boolean = true;
  clientLoginOn:boolean = false;


  ngOnInit(): void {
    this.productService.findAllProduct().subscribe({
      next: (productsList:Product[])=>{
        this.products = productsList;
      }
    })
    this.authService.currentClientLoginOn.subscribe({
      next: (clientUserLogin)=>{
        this.clientLoginOn = clientUserLogin;
      }
    })
    this.authService.currentClientData.subscribe({
      next:(client:Client)=>{
        this.client = client;
      }
    })
  }
}
