import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ClientComponent } from './client/client.component';
import { ProductComponent } from './product/product.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductGridComponent } from './product/product-grid/product-grid.component';
import { BuyCardComponent } from './buy-card/buy-card.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    ProductComponent,
    HomeComponent,
    RegisterComponent,
    ProductDetailComponent,
    ProductListComponent,
    ProductGridComponent,
    BuyCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
