import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product/product';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {
  private id!:number;
  info!:Map<string,Product>;
  public pd!:Product;
  cart:Product[] = [];

  constructor(private route: ActivatedRoute,private http: HttpClient) { 
    //cart is saved on local storage
    if (localStorage.getItem("cart") === null) {
      localStorage.setItem("cart", JSON.stringify(this.cart));
    }
    else{
      this.cart= JSON.parse(localStorage.getItem("cart")!);
    }
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => { 
      this.id=params['id'];
    });
    this.getProductFromApi();
  }

  getProductFromApi():void{
    this.http.get("http://localhost:8080/api/v1/product/"+this.id).subscribe((data) => 
    {
      var temp=Object.values(data);
      this.pd={id: temp[0],price:temp[2],name:temp[1],color:temp[3],class:temp[4],gender:temp[5],src:temp[0]};
     }
    )
  }

  addToCart(pd:Product):void{
    this.cart.push(pd);
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

}
