import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product';
import { Client } from '../profile/client';
import { DatePipe } from '@angular/common'
import {HttpClient,HttpHeaders} from '@angular/common/http';
import { throwToolbarMixedModesError } from '@angular/material/toolbar';
import { catchError, Observable } from 'rxjs';
import { Order } from '../profile/order';

@Component({
  selector: 'app-complete-order',
  templateUrl: './complete-order.component.html',
  styleUrls: ['./complete-order.component.css']
})
export class CompleteOrderComponent implements OnInit {
  public cart:Product[]=[];
  public cartOriginal:Product[]=[];
  public total=0;
  private dob="";
  private orders=[];
  private id=1;
  public count:number[]=[];
  private infoChangedFlag:boolean=false;
  private temp!:Map<string, Client>;
  public info!:Client;
  private order!:Order;
  constructor(private http: HttpClient) {
    this.cartOriginal= JSON.parse(localStorage.getItem("cart")!);
    this.total= JSON.parse(localStorage.getItem("cartTotal")!);
    for(const element of this.cartOriginal){
      if (this.count[element.id]==null){
        this.count[element.id]=1;
        this.cart.push(element);
      }
      else {
      this.count[element.id]+=1;}
    };
    console.log(this.cartOriginal);
    console.log(this.total);
    this.http.get("http://localhost:8080/api/v1/profile/"+this.id).subscribe((data) => {
      console.log(data);
      var temp = Object.values(data);
      this.info={id:temp[0],name:temp[1],dob:temp[2],sname:temp[4],snum:temp[3],pc1:temp[5],pc2:temp[6],city:temp[7]};
      this.id=temp[0];
      this.dob=temp[2];
      this.orders=temp[8];
    console.log(this.info);
    })
    
  }

  ngOnInit(): void {
    //TODO: preeencher isto
  }

  complete(){
    if(this.infoChangedFlag){
      console.log("info changed")
      this.getNewInfo();
      console.log(this.info);
      this.updateClient(this.info).subscribe(response => {console.log("res:"+response)});
      
    }
    let today =new Date().toISOString().slice(0,10);
    this.order={client:this.info,prods:this.cartOriginal,total:this.total,date:today}
    console.log(this.order);
    this.sendOrderToApi(this.order).subscribe(response => {console.log(response)});
    console.log("Order saved");
  }
  sendOrderToApi(order:Order):Observable<Order> {
    return this.http.post<Order>("http://localhost:8080/api/v1/orders/", order)
    ;
  }
  updateClient(client: Client): Observable<Client> {
        return this.http.put<Client>("http://localhost:8080/api/v1/profile/"+this.id, client)
          ;
      }
  getNewInfo():void{
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    this.info={id:this.id,name:i1.value,dob:this.dob,sname:i4.value,snum:i3.value,pc1:parseInt(i5.value),pc2:parseInt(i6.value),city:i7.value};
  }
  enableinput():void{
    this.infoChangedFlag=true;
    document.getElementById("i1")!.removeAttribute('disabled');;
    document.getElementById("i3")!.removeAttribute('disabled');;
    document.getElementById("i4")!.removeAttribute('disabled');;
    document.getElementById("i5")!.removeAttribute('disabled');;
    document.getElementById("i6")!.removeAttribute('disabled');;
    document.getElementById("i7")!.removeAttribute('disabled');;
  }

}
