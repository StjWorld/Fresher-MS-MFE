import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ProductService } from './service/product.service';
import { OrderHistoryComponent } from './components/order-history/order-history.component';
import { CheckoutComponent } from './components/checkout/checkout.component';


const routes: Routes = [
{path: 'products/:id', component: ProductDetailsComponent},
{path: 'cart-details', component: CartDetailsComponent},
{path: 'checkout', component: CheckoutComponent},
{path: 'order-history', component: OrderHistoryComponent},
{path: '', redirectTo: '/cart-details', pathMatch: 'full'},
{path: '**', redirectTo: '/cart-details', pathMatch: 'full'}];

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
