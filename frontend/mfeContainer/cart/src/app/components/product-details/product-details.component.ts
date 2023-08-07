import { Component, OnInit, untracked } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { assetUrl } from 'src/single-spa/asset-url';

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
    // this.productService.getProduct().subscribe(
    //   data => {
    //     console.log(data);

    //     this.product.imageUrl = data.imageUrl;
    //     this.product.id = "0";
        
    //      this.cartService.addToCart(this.product);

    //   }
    // )
  

  }

  addToCart(){
    console.log(`Adding to cart: ${this.product.name}, ${this.product.unitPrice}`);
    // console.log(`Adding to cart: ${this.product.vis}, ${this.product.pId}`);
    
    const theCartItem = new CartItem(this.product);
    this.cartService.addToCart(theCartItem);
  }



}