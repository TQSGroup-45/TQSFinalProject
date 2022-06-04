import { Component, OnInit } from '@angular/core';
import {Product} from '../product/product';
import {HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
selection={value:""};
originalprods:Product[]=[];
prods!:Product[];
info!:  Map<string, Product>;
cart:Product[] = [];
color:string="";

  constructor(private http: HttpClient) { 
    if (localStorage.getItem("cart") === null) {
      localStorage.setItem("cart", JSON.stringify(this.cart));
    }
    else{
      this.cart= JSON.parse(localStorage.getItem("cart")!);
    }
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8080/api/v1/store").subscribe((data) => {
      this.info = new Map<string, Product>(Object.entries(data));
      this.prods=[];
      this.info.forEach((value: Product) => {
        this.prods.push(value);
    });
      this.prods.sort((a,b)=>this.compareName(a,b));
  })
  }

  addToCart(pd:Product):void{
    this.cart.push(pd);
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

  changeOrder(event:any):void{
    let order=event.target.value;
    console.log(order);
    if(order=="Name")
      this.prods.sort((a,b)=>this.compareName(a,b));
    if(order=="Plh")
      this.prods.sort((a,b)=>this.comparePrice(a,b,"asc"));
    if(order=="Phl")
      this.prods.sort((a,b)=>this.comparePrice(a,b,"desc"));
  }

  compareName(a:Product,b:Product):number {
    if (a.name>b.name) {
      return 1;
    }
    return (b.name > a.name) ? -1 : 0;
  }

  comparePrice(a:Product,b:Product,ord:string):number {
    if (a.name==b.name) {
      return 0;
    }
    if(ord=="asc"){
    return (b.price > a.price) ? -1 : 1;
    }
    return (b.price > a.price) ? 1 : -1;
  }
  
  changeFilter():void{
    var temp=this.originalprods;
    temp=this.changeGender(temp);
    temp=this.changePrice(temp);
    temp=this.changeColor(temp);
    this.prods=temp;
  }

  changePrice(listprod:Product[]): Product[] {
    var from=document.getElementById("pricefrom") as HTMLInputElement;
    var to=document.getElementById("priceto") as HTMLInputElement;
    var pricefrom=+from.value;
    var priceto=+to.value;
    if(pricefrom<0){
      pricefrom=0
    }
    if(priceto==0){
      priceto=1000;
    }
    var temp:Product[]=[];
    listprod.forEach(element => {
      if (element.price>pricefrom && element.price<priceto){
        temp.push(element);
      }
    });
    return temp;
  }

  changeColor(listprod:Product[]): Product[]{
    if(this.color!=""){
      var temp:Product[]=[];
      listprod.forEach(element => 
      {
        if (this.color==element.color){
          temp.push(element);
        }
      });
      return temp;
    }
    else{
      return listprod;
    }
  }

  changeGender(listprod:Product[]): Product[]{
    var temp:Product[]=[];
    var m=document.getElementById("M") as HTMLInputElement;
    var f=document.getElementById("F") as HTMLInputElement;
    var u=document.getElementById("U") as HTMLInputElement;
    listprod.forEach(element => {
      if (m.checked && element.gender=="Male" || f.checked && element.gender=="Female" || u.checked && element.gender=="Undefined" ){
        temp.push(element);
      }
    });
    return temp;
  }

  changeC(event:any):void{
    if(this.color==event.target.id){
      this.color="";
    }
    else{
    this.color=event.target.id;
    }
  } 

  switchFilters():void{
    var div=document.getElementById("filter");
    var display= div!.style.display;
    if(display == "none"){
      div!.style.display = 'block';
    }
    else{
      div!.style.display = 'none';
    }
  }
}

