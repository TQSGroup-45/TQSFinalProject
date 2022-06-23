import { Component, OnInit } from '@angular/core';
import { OrderReceived } from '../profile/order';
import { HttpClient } from '@angular/common/http';
import { Client } from './client';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  name:string="";
  date:string="";
  streetnum:string="";
  streetname:string="";
  postcode1:string="";
  postcode2:string="";
  city:string="";
  orders:OrderReceived[]=[]; 
  id!:number;
  info!:Client;
  private temp!:Map<string,OrderReceived>;
  private infoChangedFlag:boolean;

  constructor(private http: HttpClient) { 
    this.infoChangedFlag=false;
  }

  ngOnInit(): void {
    this.getClientInfo();
    this.getOrders();
  }
      
  getClientInfo():void{
    this.id= parseInt(localStorage.getItem("id")!);
    this.http.get("http://localhost:8080/api/v1/clients/"+this.id).subscribe((data) => {
      var temp = Object.values(data);
      console.log(temp);
      this.info={id:temp[0],name:temp[2],dob:temp[3],sname:temp[5],snum:temp[4],pc1:temp[6],pc2:temp[7],city:temp[8]};
    }) 
  }

  getOrders():void{
    this.http.get("http://localhost:8080/api/v1/clients/"+this.id+"/orders").subscribe((data) => {
    this.temp = new Map<string, OrderReceived>(Object.entries(data)); 
    this.orders=[];
    this.temp.forEach((value: OrderReceived) => {
      this.orders.push(value);
    })})
  }
  
  enableinput():void{ 
    //removes the disabled attribute from the form fields and saves a backup of the current values
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    this.name = i1.value;
    this.date = i2.value ;
    this.streetnum = i3.value ;
    this.streetname = i4.value ;
    this.postcode1 = i5.value ;
    this.postcode2 = i6.value ;
    this.city = i7.value ;
    i1.removeAttribute('disabled');
    i2.removeAttribute('disabled');
    i3.removeAttribute('disabled');
    i4.removeAttribute('disabled');
    i5.removeAttribute('disabled');
    i6.removeAttribute('disabled');
    i7.removeAttribute('disabled');
    save!.style.display="block";
    cancel!.style.display="block";
  }

  save():void{
    //disables form fields and send new information to backend
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    i1.setAttribute('disabled','');
    i2.setAttribute('disabled','');
    i3.setAttribute('disabled','');
    i4.setAttribute('disabled','');
    i5.setAttribute('disabled','');
    i6.setAttribute('disabled','');
    i7.setAttribute('disabled','');
    save!.style.display="none";
    cancel!.style.display="none"
    this.info={id:this.info.id,name:i1.value,dob:i2.value,sname:i4.value,snum:i3.value,pc1:parseInt(i5.value),pc2:parseInt(i6.value),city:i7.value};
    this.updateClient(this.info).subscribe(response => {console.log("res:"+response)});
  }

  updateClient(client: Client): Observable<Client> {
    //send information to backend
    return this.http.put<Client>("http://localhost:8080/api/v1/clients/"+this.id, client);
  }

  cancel():void{
    // gets information from backup back to the form fields and disables them again
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    i1.value=this.name;
    i2.value=this.date;
    i3.value=this.streetnum;
    i4.value=this.streetname;
    i5.value=this.postcode1;
    i6.value=this.postcode2;
    i7.value=this.city;
    i1.setAttribute('disabled','');
    i2.setAttribute('disabled','');
    i3.setAttribute('disabled','');
    i4.setAttribute('disabled','');
    i5.setAttribute('disabled','');
    i6.setAttribute('disabled','');
    i7.setAttribute('disabled','');
    save!.style.display="none";
    cancel!.style.display="none";
    console.log(this.name,this.date,this.city)
  }
}
