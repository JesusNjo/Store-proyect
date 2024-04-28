import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit{
  productList?:Product[];
  @Output() productListChanged: EventEmitter<Product[]> = new EventEmitter<Product[]>();

  private productService = inject(ProductService);

  ngOnInit(): void {
    this.productService.findAllProduct().subscribe(products=>{
      this.productList = products;
      this.productListChanged.emit(this.productList);
    })
  }
 
}
