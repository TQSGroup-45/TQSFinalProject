import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Product,prods} from '../product/product';
import {HttpClient,HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  private id!: number;
  info!:  Map<string, Product>;
  public pd!:Product;
  constructor(private route: ActivatedRoute,private http: HttpClient) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => { 
      this.id=params['id'];
    });

    this.http.get("http://localhost:8080/api/v1/product/"+this.id).subscribe((data) => 
    {
      var temp=Object.values(data);
      this.pd={id: temp[0],price:temp[2],name:temp[1],color:temp[3],class:temp[4],gender:temp[5],src:temp[0]};
    
     }
      )};
    
    //ir buscar produto correspondente 
  
  

}
