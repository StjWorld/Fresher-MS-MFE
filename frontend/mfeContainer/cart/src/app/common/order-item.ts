import { CartItem } from "./cart-item";

export class OrderItem {
    imageUrl: string;
    unitPrice: number;
    quantity: number;
    pId?: string;
    id?: string;

    constructor(cartItem: CartItem) {
     this.imageUrl = cartItem.imageUrl;
     this.quantity = cartItem.quantity;
     this.unitPrice = cartItem.unitPrice;
     this.pId = cartItem.productId;
     this.id = cartItem.id;
    }
}