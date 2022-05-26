import { ApplicationsComponent } from './applications/applications.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourierManagementComponent } from './courier-management/courier-management.component';
import { DeliveryStatisticsComponent } from './delivery-statistics/delivery-statistics.component';
const routes: Routes = [
  {path: 'applications', component: ApplicationsComponent},
  {path: 'deliveryStatistics', component: DeliveryStatisticsComponent},
  {path: 'courierManagement', component: CourierManagementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
