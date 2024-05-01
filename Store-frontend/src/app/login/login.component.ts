import { Component, OnInit, inject } from '@angular/core';
import { Product } from '../models/product';
import { ProductService } from '../service/product/product.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  product?:Product[];
  private productService = inject(ProductService);

  ngOnInit(): void {
    this.productService.findAllProduct().subscribe({
      next: (product:Product[])=>{
        this.product = product;
      }
    })
  }
}
