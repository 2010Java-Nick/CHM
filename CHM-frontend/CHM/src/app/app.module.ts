import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterationComponent } from './components/registeration/registeration.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
// import { LogInComponent } from './components/log-in/log-in.component';
import { HttpClientModule, HttpHeaders, HttpClient } from '@angular/common/http';
import { PaymentComponent } from './components/payment/payment.component';
// import { CreateProfileComponent } from './components/create-profile/create-profile.component';



@NgModule({
  declarations: [
    AppComponent,
    RegisterationComponent,
    PaymentComponent,
    // LogInComponent, 
    // CreateProfileComponent
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


