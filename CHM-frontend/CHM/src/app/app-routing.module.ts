import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateProfileComponent } from './components/create-profile/create-profile.component';

import { LoginComponent } from './components/login/login.component';
import { ViewHomeComponent } from './components/main/view-home/view-home.component';
import { PaymentComponent } from './components/payment/payment.component';
import { RegisterationComponent } from './components/registeration/registeration.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';
import { InterestsComponent } from './components/interests/interests.component';
import { EditProfileComponent } from './components/main/edit-profile/edit-profile.component';
import { MatchListComponent } from './components/match-list/match-list.component';

const routes: Routes = [
  {path : 'signup/profile', component : CreateProfileComponent},
  {path : 'signup', component : RegisterationComponent}, 
  {path : 'interest', component : InterestsComponent}, 
  {path : 'login', component : LoginComponent}, 
  {path : 'home', component : ViewHomeComponent}, 
  {path : 'payment', component : PaymentComponent},
  {path : 'profile', component : ViewProfileComponent},
  {path : 'editProfile', component : EditProfileComponent},
  {path : 'matches', component : MatchListComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
