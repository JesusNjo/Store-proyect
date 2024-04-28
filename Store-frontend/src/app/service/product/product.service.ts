import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Pagination } from 'src/app/models/pagination';
import { Product } from 'src/app/models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url:string = "http://localhost:8080/product";
  private httpClient = inject(HttpClient);

  constructor() { }

  //Get all products
  public findAllProduct=():Observable<Product[]>=>{
    return this.httpClient.get<Product[]>(`${this.url}`);
  }
  //Get product by id
  public findProductById=(id:number):Observable<Product>=>{
    return this.httpClient.get<Product>(`${this.url}/${id}`);
  }
  //Create product
  public createProduct=(product:Product):Observable<Product>=>{
    return this.httpClient.post<Product>(`${this.url}`,product);
  }
  //FindAllProductPagin
  public productPagination=(pagination:Pagination,query:string):Observable<Product[]>=>{
    return this.httpClient.get<Product[]>(`${this.url}/query`, { params: { property: pagination.property, direction: pagination.direction, limit: pagination.limit.toString(), skip: pagination.skip.toString(), query: query.toUpperCase() } });

  }
  //FindProductBySort
  productSort(property: string, direction:string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.url}/sort`, { params: { property, direction } });
  }

  //FindProductsByQuery
  public searchByQuery(pagination: Pagination, query: string): Observable<Product[]> {
    const params = {
      limit: pagination.limit,
      skip: pagination.skip,
      direction: pagination.direction,
      property: pagination.property,
      query: query
    };
    console.log(params);
    return this.httpClient.get<Product[]>(`${this.url}/query`, { params: params });
  }
  
  
  
}
