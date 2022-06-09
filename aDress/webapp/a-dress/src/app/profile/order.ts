import { Product } from "../product/product"
import { Client } from "./client"

export interface Order {
    client:Client,
    prods:Product[],
    total:number,
    date:string
}

export interface OrderReceived {
    client:Client,
    id:number,
    prods:Product[],
    total:number,
    status:string,
    date:string
}

