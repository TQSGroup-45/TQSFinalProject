import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { StoreComponent } from './store/store.component';
import { ProfileComponent } from './profile/profile.component';
import { CartComponent } from './cart/cart.component';
import { CompleteOrderComponent } from './complete-order/complete-order.component'

const routes: Routes = [
  {path:'products', component:StoreComponent},
  {path:'products/product/:id',component:ProductComponent},
  {path:'profile',component:ProfileComponent},
  {path:'cart',component:CartComponent},
  {path:'order',component:CompleteOrderComponent},
  {path:'', component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
