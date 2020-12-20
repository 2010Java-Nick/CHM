import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateProfileComponent } from './components/create-profile/create-profile.component';
import { RegisterationComponent } from './components/registeration/registeration.component';

const routes: Routes = [
  {path : 'signup/profile', component : CreateProfileComponent},
  {path : 'signup', component : RegisterationComponent}, 
  {path: '', redirectTo: '/home', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
