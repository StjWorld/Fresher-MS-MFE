import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaymentInfo } from '../common/payment-info';
import { Purchase } from '../common/purchase';
import { CartItem } from '../common/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private purchaseUrl = 'http://localhost:8082/api/carts/purchase';

  // private paymentIntentUrl = environment.ecommerceUrl + '/checkout/payment-intent';

  constructor(private httpClient: HttpClient) { }

  placeOrder(purchase: Purchase): Observable<any> {
    return this.httpClient.post<Purchase>(this.purchaseUrl, purchase);
  }


  // createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
  //   return this.httpClient.post<PaymentInfo>(this.paymentIntentUrl, paymentInfo);
  // }
}