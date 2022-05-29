import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-complete-order',
  templateUrl: './complete-order.component.html',
  styleUrls: ['./complete-order.component.css']
})
export class CompleteOrderComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
  }

  enableinput():void{
    document.getElementById("i1")!.removeAttribute('disabled');
    document.getElementById("i3")!.removeAttribute('disabled');
    document.getElementById("i4")!.removeAttribute('disabled');
    document.getElementById("i5")!.removeAttribute('disabled');
    document.getElementById("i6")!.removeAttribute('disabled');
    document.getElementById("i7")!.removeAttribute('disabled');
  }
}
