import { Component, OnInit, inject } from '@angular/core';
import { Product } from '../models/product';
import { ProductService } from '../service/product/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products?: Product[];
  private productService = inject(ProductService);


  ngOnInit(): void {
    this.productService.findAllProduct().subscribe({
      next: (productsList:Product[])=>{
        this.products = productsList;
      }
    })
  }
}
