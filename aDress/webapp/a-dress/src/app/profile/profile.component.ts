import { Component, OnInit } from '@angular/core';

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
  
  constructor() { }

  ngOnInit(): void {
    
  }
  enableinput():void{
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    this.name = i1!.value;
    this.date = i2!.value ;
    this.streetnum = i3!.value ;
    this.streetname = i4!.value ;
    this.postcode1 = i5!.value ;
    this.postcode2 = i6!.value ;
    this.city = i7!.value ;
    i1!.removeAttribute('disabled');
    i2!.removeAttribute('disabled');
    i3!.removeAttribute('disabled');
    i4!.removeAttribute('disabled');
    i5!.removeAttribute('disabled');
    i6!.removeAttribute('disabled');
    i7!.removeAttribute('disabled');
    save!.style.display="block";
    cancel!.style.display="block"
    console.log(this.name,this.date,this.city)
  }
  save():void{
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    i1!.setAttribute('disabled','');
    i2!.setAttribute('disabled','');
    i3!.setAttribute('disabled','');
    i4!.setAttribute('disabled','');
    i5!.setAttribute('disabled','');
    i6!.setAttribute('disabled','');
    i7!.setAttribute('disabled','');
    save!.style.display="none";
    cancel!.style.display="none"
    console.log(this.name,this.date,this.city)
  }
  cancel():void{
    var save = document.getElementById("confirmchanges");
    var cancel = document.getElementById("cancelchanges");
    var i1=document.getElementById("i1") as HTMLInputElement;
    var i2=document.getElementById("i2") as HTMLInputElement;
    var i3=document.getElementById("i3") as HTMLInputElement;
    var i4=document.getElementById("i4") as HTMLInputElement;
    var i5=document.getElementById("i5") as HTMLInputElement;
    var i6=document.getElementById("i6") as HTMLInputElement;
    var i7=document.getElementById("i7") as HTMLInputElement;
    i1!.value=this.name;
    i2!.value=this.date;
    i3!.value=this.streetnum;
    i4!.value=this.streetname;
    i5!.value=this.postcode1;
    i6!.value=this.postcode2;
    i7!.value=this.city;
    i1!.setAttribute('disabled','');
    i2!.setAttribute('disabled','');
    i3!.setAttribute('disabled','');
    i4!.setAttribute('disabled','');
    i5!.setAttribute('disabled','');
    i6!.setAttribute('disabled','');
    i7!.setAttribute('disabled','');
    save!.style.display="none";
    cancel!.style.display="none";
    console.log(this.name,this.date,this.city)
  }
}
