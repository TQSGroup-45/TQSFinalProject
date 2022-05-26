import { Component, OnInit } from '@angular/core';
import {Product,prods} from '../product/product';
@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
selection={value:""};
originalprods:Product[]=prods;
prods:Product[]=prods;
color:String="";
  constructor() { }

  ngOnInit(): void {
    this.prods.sort((a,b)=>(a.name> b.name) ? 1 : temp);
  }
  changeOrder(event:any):void{
    let order=event.target.value;
    console.log(order);
    if(order=="Name")
      this.prods.sort((a,b)=>(a.name> b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
    if(order=="Plh")
      this.prods.sort((a,b)=>(a.price> b.price) ? 1 : ((b.price > a.price) ? -1 : 0));
    if(order=="Phl")
      this.prods.sort((a,b)=>(a.price> b.price) ? -1 : ((b.price > a.price) ? 1 : 0));
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
    listprod.forEach(element => {
      if (this.color==element.color){
        temp.push(element);
      }
    });
    return temp;
  }
  else{
    return listprod;}
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

