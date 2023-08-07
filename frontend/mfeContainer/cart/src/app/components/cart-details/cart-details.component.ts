import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { assetUrl } from 'src/single-spa/asset-url';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  product: Product = new Product();

  constructor(private cartService: CartService,private productService: ProductService,private route: ActivatedRoute) {}

 
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
        // this.product.id="1";
        // this.product.sku="ededd33f44ff4f44343";
        // this.product.name= "Playstaion 5";
        // this.product.description = "Call of Duty Edition";
        // this.product.unitPrice = 400.00;
        // this.product.imageUrl=  assetUrl("/images/ps4.jpg");
        // console.log("hello"+assetUrl("/images/ps4.jpg"));
        // this.product.active = true;
        // this.product.quantity =1;
        // this.product.unitInStock = 100;
        // this.product.dateCreated;
        // this.product.lastUpdated;
        // this.cartService.addToCart(this.product);
    })
    this.listCartDetails(); 
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