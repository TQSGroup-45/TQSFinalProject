import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product';
import { Client } from '../profile/client';

@Component({
  selector: 'app-complete-order',
  templateUrl: './complete-order.component.html',
  styleUrls: ['./complete-order.component.css']
})
export class CompleteOrderComponent implements OnInit {
  public cart:Product[]=[];
  public cartOriginal:Product[]=[];
  public total=0;
  
  public count:number[]=[];
  private infoChangedFlag:boolean=false;
  public info:Client={name:"Andreia",dob:"2001-02-21",sname:"rua",snum:"2",postcode1:123,postcode2:456,city:"Narnia"};
  constructor() {
    this.cartOriginal= JSON.parse(localStorage.getItem("cart")!);
    this.total= JSON.parse(localStorage.getItem("cartTotal")!);
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

  ngOnInit(): void {
    //TODO: preeencher isto
  }
  carregarCarrinho():void{
    //TODO: ir buscar produtos no carrinho a API
  }
  enviarCarrinhoParaApi():void{
    //TODO: enviar lista e total
  }

  carregarInfo(){
  //TODO: ir buscar informacoes do cliente a API
  }

  complete(){
    if(this.infoChangedFlag){
      //getNewInfo();
      //enviar novas informacoes para API
    }
    //enviar order para citydelivery
    // enviar this.total
    console.log("Order saved");
  }
  enableinput():void{
    this.infoChangedFlag=true;
    document.getElementById("i1")!.removeAttribute('disabled');
    document.getElementById("i3")!.removeAttribute('disabled');
    document.getElementById("i4")!.removeAttribute('disabled');
    document.getElementById("i5")!.removeAttribute('disabled');
    document.getElementById("i6")!.removeAttribute('disabled');
    document.getElementById("i7")!.removeAttribute('disabled');
  }

}
