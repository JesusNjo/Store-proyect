import { Component, EventEmitter,Input, OnInit, Output, inject } from '@angular/core';
import { Pagination } from 'src/app/models/pagination';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit{


  productList?:Product[];

  private productService = inject(ProductService);
  currentSortDirectionName: string = 'ASC';
  currentSortDirectionPrice: string = 'ASC';
  currentSortDirectionCategory: string = 'ASC';

  pagin:Pagination={
    property: ['name','category'],
    direction: 'ASC',
    skip: 0,
    limit: 10
  };
  query:string ='';

  ngOnInit(): void {
    this.productService.findAllProduct().subscribe(products=>{
      this.productList = products;
    })
  }

  onSearch(): void {
    this.findProductsByQuery();
  }


  sortProductsName(property: string,direction:string): void {
    this.currentSortDirectionName = direction;
  
    this.productService.productSort(property, direction).subscribe(sortedProducts => {
      this.productList = sortedProducts;
    });
  }
  sortProductsPrice(property: string,direction:string): void {
    this.currentSortDirectionPrice = direction;
  
    this.productService.productSort(property, direction).subscribe(sortedProducts => {
      this.productList = sortedProducts;
    });
  }
  sortProductsCategory(property: string,direction:string): void {
    this.currentSortDirectionCategory = direction;
  
    this.productService.productSort(property, direction).subscribe(sortedProducts => {
      this.productList = sortedProducts;
    });
  }

  findProductsByQuery(){
    this.productService.searchByQuery(this.pagin,this.query).subscribe(search=>{
      this.productList = search;
    })
  }
  
  
  

}
