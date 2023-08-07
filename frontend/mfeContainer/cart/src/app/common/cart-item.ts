import { Product } from "./product";

export class CartItem {


    id: string;
    pId?: string;
    name: string;

    description: string;

    imageUrl: string;
    unitPrice: number;
    quantity: number;

    constructor(product: Product) {
        this.id = product.id;
        this.description = product.description;
        this.name = product.name;
        this.imageUrl = product.imageUrl;
        this.unitPrice = product.unitPrice;

        this.quantity = 1;
    }
}
