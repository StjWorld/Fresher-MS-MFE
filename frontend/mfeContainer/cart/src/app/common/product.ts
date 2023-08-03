export class Product {

    id!: string;
    sku!: string;
    name!: string;
    description!: string;
    unitPrice!: number;
    imageUrl!: string;
    active!: boolean;
    quantity!: number;
    unitInStock!: number;
    dateCreated!: Date;
    lastUpdated!: Date;
  private _embedded: any;
}