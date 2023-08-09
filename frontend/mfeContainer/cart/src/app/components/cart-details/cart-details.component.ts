import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { CartItems } from 'src/app/common/cart-items';
import { Product } from 'src/app/common/product';
import { Purchase } from 'src/app/common/purchase';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { assetUrl } from 'src/single-spa/asset-url';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] =[];
  cartBackendItems: CartItems[] =[];
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
    // get cart items
    const cartItems = this.cartService.cartItems;

    // - short way of doing the same thing
    // let orderItems: OrderItem[] = cartItems.map(tempCartItem => new OrderItem(tempCartItem));

    let cartBackendItems = cartItems.map(tempCartItem => new CartItems(tempCartItem));

    let purchase = new Purchase();

    purchase.id = cartBackendItems[0].cart;
    purchase.cartDate = new Date();
    purchase.cartItems = cartBackendItems;

    this.cartService.createCart(purchase).subscribe(
      {
      next:response => {
        // alert(`Your order has been recieved.\nOrder tracking number: ${response.orderTrackingNumber}`)
        
        
        //reset cart
        // this.cartService.cartItems = [];
        // this.cartService.totalPrice.next(0);
        // this.cartService.totalQuantity.next(0);
        // this.cartService.persistCartItems();
    
      
    
        // navigate back to the products page
        // this.router.navigateByUrl("/cart");


      },
      error: err => {
        alert(`There was an error: ${err.message}`);
      }
  });
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