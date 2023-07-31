import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { CartItem } from '../common/cart-item';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  private cartUrl = environment.ecommerceUrl;
  
  cartItems: CartItem[] = [];

  isCartSaved: boolean = false;

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  //storage: Storage = localStorage;
  storage: Storage = sessionStorage;

  constructor(private httpClient: HttpClient) {

    //read data from storage
    let data = JSON.parse(this.storage.getItem('cartItems')!);

    this.isCartSaved = JSON.parse(this.storage.getItem('isCartSaved')!);

    if(this.isCartSaved ==null)
    {
      this.isCartSaved = false;
      this.storage.setItem('isCartSaved', JSON.stringify(this.isCartSaved));
    }

    if(data != null){
      this.cartItems = data;

      //compute totals based on the data that is read from storage
      this.computeCartTotals();
    }
   }

  

  addToCart(theCartItem: CartItem) {

    //check if we already have the item in our cart
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = CartItem as unknown as CartItem;

    if(this.cartItems.length > 0) {
//fimd the item in the cart based on item id
      existingCartItem = this.cartItems.find(tempCartItem => tempCartItem.id === theCartItem.id)!;


      //check if we found it
      alreadyExistsInCart = (existingCartItem != undefined);

    }
    
    if(alreadyExistsInCart) {
      //increment the quantity
      existingCartItem.quantity++;
    }
    else{
      //just add the item to the array
      this.cartItems.push(theCartItem);
    }
    //compite cart total price and total quantity
    this.computeCartTotals();
    


  }
  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for(let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    //publish the new values /... all subscribers will recieve new data
  this.totalPrice.next(totalPriceValue);
  this.totalQuantity.next(totalQuantityValue);

  //log cart data just for debugging purposes
  this.logCartData(totalPriceValue, totalQuantityValue);

  //persist cart data
  this.persistCartItems();

  }

  persistCartItems() {
    this.storage.setItem('cartItems', JSON.stringify(this.cartItems));
    this.storage.setItem('isCartSaved', JSON.stringify(this.isCartSaved));
  }


  logCartData(totalPriceValue: number, totalQuantityValue: number) {
    console.log('Contents of the cart');
    for(let tempCartItem of this.cartItems){
      const subTotalPrice = tempCartItem.quantity * tempCartItem.unitPrice;
      console.log(`name: ${tempCartItem.name}, quantity=${tempCartItem.quantity}, unitPrice=${tempCartItem.unitPrice}, subTotalPrice=${subTotalPrice}`);
  
    }

    console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`);
    console.log('-----')
  }

  decrementQuantity(theCartItem: CartItem) {
    
    theCartItem.quantity--;

    if(theCartItem.quantity === 0) {
      this.isCartSaved = false;
      this.remove(theCartItem);
      this.isCartSaved = false;
      this.storage.setItem('isCartSaved', JSON.stringify(this.isCartSaved));
      this.deleteCart();
    }
    else {
      this.computeCartTotals();
    }
}


createCart(): Observable<any> {
 
  this.isCartSaved = true;

  console.log("cart items saved : ",this.isCartSaved);

  this.storage.setItem('isCartSaved', JSON.stringify(this.isCartSaved));

  return this.httpClient.post<CartItem>(this.cartUrl + "/create-cart", this.cartItems);
}

updateCart(): Observable<any> {
 
  this.isCartSaved = true;

  console.log("cart items updated");

  return this.httpClient.post<CartItem>(this.cartUrl + "/update-cart", this.cartItems);

}

  remove(theCartItem: CartItem) {
    //get intex of item in the array
    const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.id === theCartItem.id );
    //if found, remove the item from the array at the given index
    if(itemIndex > -1){
      this.cartItems.splice(itemIndex, 1);

      this.computeCartTotals();
    }
  }

  deleteCart(): Observable<any>{

  console.log("cart items saved : ",this.isCartSaved);

  return this.httpClient.post<CartItem>(this.cartUrl + "/delete-cart", this.cartItems);

}

}


