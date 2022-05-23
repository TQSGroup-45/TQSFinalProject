import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import {Product,prods} from '../product/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  private id!: number;
  
  public pd!:Product;
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    this.route.params.subscribe(params => { 
      this.id=params['id'];
      
    });
    console.log(this.id);
    this.pd=prods[this.id];
    //ir buscar produto correspondente 
  }
  

}
