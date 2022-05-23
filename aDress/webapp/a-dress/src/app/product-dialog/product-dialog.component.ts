import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product';
@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.css']
})
export class ProductDialogComponent implements OnInit {
  constructor( ) { }

  ngOnInit(): void {
  }
 
}
 export interface ProdDialogData {
    product: Partial<Product>;
    enableDelete:boolean;
  }