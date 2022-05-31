import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public products:Product[]=[
    {id: 0,price:39.99,name:"Brown pants",color:"Brown",class:"Pants",gender:"Male",src:"1"},
    {id: 1,price:29.99,name:"Blue T-shirt",color:"Blue",class:"T-shirt",gender:"Male",src:"2"}];
    public total=0;
  constructor() { 
    for(const element of this.products){
      this.total+=element.price;
    }
    

  }

  ngOnInit(): void {
    // TODO document why this method 'ngOnInit' is empty
   
  }
  carregarCarrinho():void{
    //TODO: ir buscar produtos no carrinho a API
  }
  enviarCarrinhoParaApi():void{
    //TODO: enviar lista e total
  }

}
