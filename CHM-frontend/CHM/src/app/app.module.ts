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
import { PotenMatchListComponent } from './components/poten-match-list/poten-match-list.component';
import { PotenMatchListItemComponent } from './components/poten-match-list-item/poten-match-list-item.component';
import { MatchListComponent } from './components/match-list/match-list.component';
import { MatchListItemComponent } from './components/match-list-item/match-list-item.component';
import { ProfileCardComponent } from './components/profile-card/profile-card.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout'
import { PhotoUploadComponent } from './components/photo-upload/photo-upload.component';



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
    PotenMatchListComponent,
    PotenMatchListItemComponent,
    MatchListComponent,
    MatchListItemComponent,
    ProfileCardComponent,
    PhotoUploadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    FlexLayoutModule,
    MatCardModule, 
    MatIconModule
  ],

  providers: [HttpClient],

  bootstrap: [AppComponent]
})

export class AppModule { }


