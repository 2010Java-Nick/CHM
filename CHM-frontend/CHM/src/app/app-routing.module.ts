import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateProfileComponent } from './components/create-profile/create-profile.component';

import { LoginComponent } from './components/login/login.component';
import { ViewHomeComponent } from './components/main/view-home/view-home.component';
import { PaymentComponent } from './components/payment/payment.component';
import { RegisterationComponent } from './components/registeration/registeration.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';

const routes: Routes = [
  {path : 'signup/profile', component : CreateProfileComponent},
  {path : 'signup', component : RegisterationComponent}, 
  {path : 'login', component : LoginComponent}, 
  {path : 'home', component : ViewHomeComponent}, 
  {path : 'payment', component : PaymentComponent},
  {path : 'profile', component : ViewProfileComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'} 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
