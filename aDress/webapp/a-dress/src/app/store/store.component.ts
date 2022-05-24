import { Component, OnInit } from '@angular/core';
import {Product,prods} from '../product/product';
@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
selection={value:""};
prods:Product[]=prods;
  constructor() { }

  ngOnInit(): void {
  }
  changePage(page:string):void{}

}
