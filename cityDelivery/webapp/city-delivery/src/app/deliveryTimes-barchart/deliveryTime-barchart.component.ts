import { Component, OnInit } from '@angular/core';
import { averageTimes } from '../data/product';
@Component({
  selector: 'app-deliveryTime-barchart',
  templateUrl: './deliveryTime-barchart.component.html',
})
export class DeliveryTimeBarchartComponent implements OnInit {

  averageTimes: any[] ;
  view: [number,number] = [0,0];

  // options
  legend: boolean = true;
  legendTitle: string = 'Tempo Espera';

  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Mês';
  yAxisLabel: string = 'Tempo';
  timeline: boolean = true;



  colorScheme = {
    domain: ['#704FC4', '#4B852C', '#B67A3D', '#5B6FC8', '#25706F']
  };
  constructor() {
    Object.assign(this, { averageTimes });
  }

  ngOnInit(): void {
     //Empty for now
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
