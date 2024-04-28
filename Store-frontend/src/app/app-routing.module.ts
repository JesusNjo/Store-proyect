import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductGridComponent } from './product/product-grid/product-grid.component';
import { RegisterComponent } from './register/register.component';
import { ClientComponent } from './client/client.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "product", component:ProductComponent, children:[
    {path: "list", component:ProductListComponent},
    {path: "grid", component:ProductGridComponent},
    {path: ":id", component:ProductDetailComponent}
  ]
  },
  {path: 'register', component:RegisterComponent},
  {path: 'client', component:ClientComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
