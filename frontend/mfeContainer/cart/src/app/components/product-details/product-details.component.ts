import { Component, OnInit, untracked } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product();

  constructor(private productService: ProductService,
              private cartService: CartService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    })
  }

  handleProductDetails() {

    //get the "id" param string. convert string to a number using the + symbol
    // const theProductId: number = +this.route.snapshot.paramMap.get('id')!;

    // uses get request to url
    this.productService.getProduct().subscribe(
      data => {
        console.log(data);

        this.product.imageUrl = data.imageUrl;
        this.product.id = "0";
        
         this.cartService.addToCart(this.product);

      }
    )
  
      
  
    // this.product = new Product();
    // this.product.id="1";
    // this.product.sku="ededd33f44ff4f44343";
    // this.product.name= "Playstaion 5";
    // this.product.description = "Call of Duty Edition";
    // this.product.unitPrice = 400.00;
    // this.product.imageUrl= "./assets/images/ps4.jpg";
    // this.product.active = true;
    // this.product.quantity =1;
    // this.product.unitInStock = 100;
    // this.product.dateCreated;
    // this.product.lastUpdated;
    // gets product to add to cart 
    // this.cartService.addToCart(this.product);

    // this.product = new Product();
    // this.product.id="2";
    // this.product.sku="4kfforf3kwfwfpeiedndd32d2";
    // this.product.name= "Turtle Beach Headset";
    // this.product.description = "color black";
    // this.product.unitPrice = 350.00;
    // this.product.imageUrl= "./assets/images/headset.jpg";
    // this.product.active = true;
    // this.product.quantity =1;
    // this.product.unitInStock = 150;
    // this.product.dateCreated;
    // this.product.lastUpdated;

    // this.cartService.addToCart(this.product);


    // this.product = new Product();
    // this.product.id="3";
    // this.product.sku="nonon4nff44f4f4444";
    // this.product.name= "Call of Duty Black Ops II";
    // this.product.description = "multiplayer game";
    // this.product.unitPrice = 45.00;
    // this.product.imageUrl= "./assets/images/game.jpg";
    // this.product.active = true;
    // this.product.quantity =1;
    // this.product.unitInStock = 300;
    // this.product.dateCreated;
    // this.product.lastUpdated;

    // this.cartService.addToCart(this.product);
  }

  addToCart(){
    console.log(`Adding to cart: ${this.product.name}, ${this.product.unitPrice}`);
    // console.log(`Adding to cart: ${this.product.vis}, ${this.product.pId}`);
    
    const theCartItem = new CartItem(this.product);
    this.cartService.addToCart(theCartItem);
  }



}