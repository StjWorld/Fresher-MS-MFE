import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  isCartSaved!: boolean;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.listCartDetails();
    console.log("c "+this.cartService.isCartSaved);
    this.isCartSaved = this.cartService.isCartSaved;
     
  }
  listCartDetails() {
    //get a handle to the cart items
    this.cartItems = this.cartService.cartItems;
    //scbscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );
    //scbscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    //compute cart total price and quantity
    this.cartService.computeCartTotals();
  }

  incrementQuantity(theCartItem: CartItem) {
    this.cartService.addToCart(theCartItem);
  }

  decrementQuantity(theCartItem: CartItem) {
    this.cartService.decrementQuantity(theCartItem);
  }

  createCart(){
    this.cartService.createCart();
  }

  updateCart(){
    this.cartService.updateCart();
  }

  remove(theCartItem: CartItem) {
    this.cartService.remove(theCartItem);
  }

  deleteCart(){
    this.cartService.deleteCart();
  }

}
