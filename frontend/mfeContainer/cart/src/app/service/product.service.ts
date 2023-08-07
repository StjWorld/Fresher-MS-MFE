import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ProductService {


  private baseUrl = '/api/products';


  constructor(private httpClient: HttpClient) { }

  getProduct(): Observable<Product> {
    //build url based on product id
    const productUrl = `${this.baseUrl}`;
    return this.httpClient.get<Product>("products");
  }


  private getProducts(): Observable<Product[]> {
    return this.httpClient.get<GetResponseProducts>(this.baseUrl).pipe(map(response => response._embedded.products));
  }

}


interface GetResponseProducts {
  _embedded: {
    products: Product[];
  }
}