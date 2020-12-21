import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterationComponent } from './components/registeration/registeration.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
// import { LogInComponent } from './components/log-in/log-in.component';
import { HttpClientModule, HttpHeaders, HttpClient } from '@angular/common/http';

//import { LoginComponent } from './components/login/login.component';

import { CreateProfileComponent } from './components/create-profile/create-profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { NavbarComponent } from './components/main/navbar/navbar.component';
import { MatchDetailComponent } from './components/main/match-detail/match-detail.component';
import { ViewHomeComponent } from './components/main/view-home/view-home.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';






@NgModule({
  declarations: [
    AppComponent,
    RegisterationComponent,
    PaymentComponent,
    // LogInComponent, 
    CreateProfileComponent,
    NavbarComponent,
    MatchDetailComponent,
    ViewHomeComponent,
    ViewProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],

  providers: [HttpClient],

  bootstrap: [AppComponent]
})

export class AppModule { }


