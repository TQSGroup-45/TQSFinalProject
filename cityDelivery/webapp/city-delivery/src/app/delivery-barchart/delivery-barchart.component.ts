import { Component, OnInit } from '@angular/core';

import { productSales, productSalesMulti } from '../data/product';

@Component({
  selector: 'app-delivery-barchart',
  templateUrl: './delivery-barchart.component.html',
  styleUrls: ['./delivery-barchart.component.css']
})
export class DeliveryBarchartComponent implements OnInit {

  productSales: any[]
  productSalesMulti: any[]

  view: [number,number] = [0,0];


  // options
  legendTitle: string = 'Entregas';
  legendTitleMulti: string = 'Months';
  legendPosition: string = 'below'; // ['right', 'below']
  legend: boolean = true;

  xAxis: boolean = true;
  yAxis: boolean = true;

  yAxisLabel: string = 'Entregas';
  xAxisLabel: string = 'Mês';
  showXAxisLabel: boolean = true;
  showYAxisLabel: boolean = true;

  maxXAxisTickLength: number = 30;
  maxYAxisTickLength: number = 30;
  trimXAxisTicks: boolean = false;
  trimYAxisTicks: boolean = false;
  rotateXAxisTicks: boolean = false;

  xAxisTicks: any[] = ['Genre 1', 'Genre 2', 'Genre 3', 'Genre 4', 'Genre 5', 'Genre 6', 'Genre 7']
  yAxisTicks: any[] = []

  animations: boolean = true; // animations on load

  showGridLines: boolean = true; // grid lines

  showDataLabel: boolean = true; // numbers on bars

  gradient: boolean = false;
  colorScheme = {
    domain: ['#704FC4', '#4B852C', '#B67A3D', '#5B6FC8', '#25706F']
  };
  schemeType: string = 'ordinal'; // 'ordinal' or 'linear'

  activeEntries: any[] = ['book']
  barPadding: number = 5
  tooltipDisabled: boolean = false;

  yScaleMax: number = 9000;

  roundEdges: boolean = false;

  constructor() {Object.assign(this ,{productSales}) }

  ngOnInit(): void {
  }

}







