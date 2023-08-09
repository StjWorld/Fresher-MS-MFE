import { Product } from "./product";

export class CartItem {


    id: string;
    productId?: string;
    orderId!:number;
    name: string;
    description: string;
    imageUrl: string;
    unitPrice: number;
    quantity: number;


    constructor(product: Product) {
        this.id = product.id;
        this.orderId = product.orderId;
        this.description = product.description;
        this.name = product.name;
        this.imageUrl = product.imageUrl;
        this.unitPrice = product.unitPrice;

        this.quantity = 1;
    }
}
