import { CartItem } from "./cart-item";

export class CartItems {


    itemId?: number;

    productId!:string;

    quantity!:number;

    cart!:number;

    orderId!:number;


    constructor(cartItem: CartItem) {
     
        this.quantity = cartItem.quantity;
        this.orderId = cartItem.orderId;
        this.cart = cartItem.orderId;
        this.productId = cartItem.id;
       }

}
