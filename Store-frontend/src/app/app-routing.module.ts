import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductGridComponent } from './product/product-grid/product-grid.component';
import { RegisterComponent } from './register/register.component';
import { ClientComponent } from './client/client.component';
import { SellProductComponent } from './product/sell-product/sell-product.component';
import { BuyCardComponent } from './buy-card/buy-card.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "product", component:ProductComponent, children:[
    {path: "list", component:ProductListComponent},
    {path: "grid", component:ProductGridComponent},
    {path: ":id", component:ProductDetailComponent}
  ]
  },
  {path: 'register', component:RegisterComponent},
  {path: 'client', component:ClientComponent},
  {path: 'sell', component:SellProductComponent},
  {path: 'buycar', component: BuyCardComponent},
  {path: 'login', component:LoginComponent},

  {path: 'inicio', component: DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
