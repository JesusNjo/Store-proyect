import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-sell-product',
  templateUrl: './sell-product.component.html',
  styleUrls: ['./sell-product.component.css']
})
export class SellProductComponent implements OnInit {

  sellForm:FormGroup;
  private productService = inject(ProductService);

  constructor(form:FormBuilder){
    this.sellForm = form.group({
      name: ['',Validators.required],
      price: ['',Validators.required],
      category: ['',Validators.required],
      description: ['',Validators.required],
      image: ['',Validators.required]

    })
  }


  ngOnInit(): void {
    
  }


  sendInformation(){
    if(this.sellForm.valid){
      this.productService.createProduct(this.sellForm.value).subscribe({
        next:(product:Product)=>{
          product = this.sellForm.value;
          const confirmation = confirm('¿Are you sure to create this product?');
          if(confirmation){
            alert(`${product.name} was created successfull.`);
          }
        },error:(error:Error)=>{
          console.log(error);
          
        }
      })
    }
  }

  hasErrors(field:string,type:string){
    return this.sellForm.get(field)?.hasError(type) && this.sellForm.get(field)?.touched;
  }

}
