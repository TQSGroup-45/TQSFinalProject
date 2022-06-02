import { Component, OnInit } from '@angular/core';
import { averageTimes } from '../data/product';
@Component({
  selector: 'app-deliveryTime-barchart',
  templateUrl: './deliveryTime-barchart.component.html',
  styleUrls: ['./deliveryTime-barchart.component.css']
})
export class DeliveryTimeBarchartComponent implements OnInit {

  averageTimes: any[] ;
  view: [number,number] = [1500, 800];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Year';
  yAxisLabel: string = 'Population';
  timeline: boolean = true;
  legendTitle: string = 'Deliveries Time';


  colorScheme = {
    domain: ['#704FC4', '#4B852C', '#B67A3D', '#5B6FC8', '#25706F']
  };
  constructor() {
    Object.assign(this, { averageTimes });
  }

  ngOnInit(): void {
  }


  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

}
