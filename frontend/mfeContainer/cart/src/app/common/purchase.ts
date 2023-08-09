import { Address } from "./address";
import { Order } from "./order";
import { OrderItem } from "./order-item";
import { CartItems } from "./cart-items";

export class Purchase {

    id!:number;
    cartDate!: Date;
    cartItems!:CartItems[];
    // shippingAddress!: Address;
    // order!: Order;
    // orderItems!: OrderItem[];

}
