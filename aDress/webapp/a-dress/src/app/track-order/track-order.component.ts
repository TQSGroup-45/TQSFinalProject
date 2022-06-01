/// <reference types="@types/google.maps" />
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loader } from '@googlemaps/js-api-loader';
@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.css']
})
export class TrackOrderComponent implements OnInit {
  private API_KEY:string="AIzaSyAgaH3w-lIbEwm5rWu2c-MIvziIWqoHhF8";
  public code!: string;
  public map!: google.maps.Map;
  private marker!: google.maps.Marker;
  //public center: google.maps.MapOptions = { center: {lat: 30, lng: -110}, zoom: 8, mapId: '1234' };
  private myLatLng = { lat: 40.632084, lng:-8.6606357 };

  constructor(private route: ActivatedRoute) {
    //empty
   }

  ngOnInit(): void {
    let loader=new Loader({apiKey:this.API_KEY});
    loader.load().then(()=>{
      this.map = new google.maps.Map(document.getElementById("map") as HTMLElement, { center: this.myLatLng, zoom: 15});
      this.marker=new google.maps.Marker({
        position: this.myLatLng,
        map:this.map,
        title: "Order",
      });
    console.log("map init");
    console.log(this.map);
    })
    this.route.params.subscribe(params => { 
      this.code=params['code'];
    });
    console.log(this.code);
  }
}
