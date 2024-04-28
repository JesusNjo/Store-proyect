import { Component, inject } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.css']
})
export class ProductGridComponent {
  productList?:Product[];
  private productService = inject(ProductService);




  ngOnInit(): void {
   this.productService.findAllProduct().subscribe({
    next: (products:Product[])=>{
      this.productList = products;
    }
   })
  }
}
