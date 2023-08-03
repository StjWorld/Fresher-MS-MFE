import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { OrderHistory } from '../common/order-history';
import { Order } from '../common/order';

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {


  private orderUrl = environment.ecommerceUrl + '/orders';

  constructor(private httpClient: HttpClient,private orders : OrderHistory) {}
 
  // getOrderHistory(theEmail: string): Observable<GetResponseOrderHistory>{

  //   //need to build URL based on the customer email
  //   const orderHistoryUrl = `${this.orderUrl}/search/findByCustomerEmailOrderByDateCreatedDesc?email=${theEmail}`;

  //   return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  // }

  getOrders(): Observable<OrderHistory[]> {
    return this.httpClient.get<GetResponseOrderHistory>(this.orderUrl + "/history/"+ this.orders.id).pipe(map(response => response._embedded.orders));
  }
}

interface GetResponseOrderHistory {
  _embedded: {
    orders: OrderHistory[];
    
  }
}