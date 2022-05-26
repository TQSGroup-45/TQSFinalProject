import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApplicationsComponent } from './applications/applications.component';
import { ApplicationsTableComponent } from './applications-table/applications-table.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon'
import { MatButtonModule } from '@angular/material/button';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MapComponent } from './components/map/map.component';
import { DeliveryStatisticsComponent } from './delivery-statistics/delivery-statistics.component';
import { DeliveryBarchartComponent } from './delivery-barchart/delivery-barchart.component';
import { NgxChartsModule }from '@swimlane/ngx-charts';
import { CourierManagementComponent } from './courier-management/courier-management.component';

@NgModule({
  declarations: [
    AppComponent,
    ApplicationsComponent,
    ApplicationsTableComponent,
    MapComponent,
    DeliveryStatisticsComponent,
    DeliveryBarchartComponent,
    CourierManagementComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    MDBBootstrapModule,
    NgxChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
