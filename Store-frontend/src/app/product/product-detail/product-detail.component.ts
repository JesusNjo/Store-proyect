import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product?:Product;
  private router = inject(ActivatedRoute);
  private productService = inject(ProductService);
  loading:boolean= true;

  ngOnInit(): void {
    setTimeout(() => {
      this.router.params.subscribe(params=>{
        this.productService.findProductById(params['id']).subscribe({
          next: (product:Product)=>{
            this.product = product;
            this.loading = false;
          }
        })
      })
    }, 1000);
  }
}
