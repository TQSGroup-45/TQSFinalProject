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
    this.http.get("http://localhost:8080/api/v1/products/"+this.id).subscribe((data) => 
    {

      var temp=Object.values(data);
      console.log(temp);
      this.pd={id: temp[0],name:temp[1],price:temp[2],color:temp[3],gender:temp[4],type:temp[4],src:temp[0]};
      console.log(this.pd.gender)
    }
    )
    
  }

  addToCart(pd:Product):void{
    this.cart.push(pd);
    console.log(pd);
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

}
