import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public cartOriginal:Product[]=[];
  public cart:Product[]=[];
  public total=0;
  public count:number[]=[];
  constructor() { 
    if (localStorage.getItem("cart") === null) {
      localStorage.setItem("cart", JSON.stringify(this.cartOriginal));
    }
    else{
      this.cartOriginal= JSON.parse(localStorage.getItem("cart")!);
    }
    for(const element of this.cartOriginal){
      this.total+=element.price;
      if (this.count[element.id]==null){
        this.count[element.id]=1;
        this.cart.push(element);
      }
      else {
      this.count[element.id]+=1;}
    }
  }
  removeFromCart(p:Product):void{
    var temp:Product[]=[];
    for(const element of this.cartOriginal){
      if(element.id!=p.id){
        temp.push(element);
      }
    }
    this.cartOriginal= temp;
    localStorage.setItem("cart", JSON.stringify(this.cartOriginal));
    window.location.reload();
  }

  ngOnInit(): void {
    // TODO document why this method 'ngOnInit' is empty
   
  }
 
  sendCart():void{
    //TODO: enviar lista e total
    localStorage.setItem("cartTotal", JSON.stringify(this.total));
  }

}
