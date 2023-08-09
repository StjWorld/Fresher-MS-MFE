import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductService } from './service/product.service';
import { CheckoutComponent } from './components/checkout/checkout.component';


const routes: Routes = [
  {path: 'products', component: ProductDetailsComponent},
  {path: 'cart', component: CartDetailsComponent},
  {path: 'checkout', component: CheckoutComponent}
];
// RouterModule.forRoot(routes),
@NgModule({
  imports: [RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule],
  exports: [RouterModule],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppRoutingModule { }
