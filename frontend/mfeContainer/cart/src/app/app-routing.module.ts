import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { ProductDetailsComponent } from './components/product-details/product-details.component';


// const routes: Routes = [{path: 'product', component: ProductDetailsComponent}];
// RouterModule.forRoot(routes),
@NgModule({
  imports: [
    HttpClientModule,
    BrowserModule],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppRoutingModule { }
