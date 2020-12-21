import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterationComponent } from './components/registeration/registeration.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HttpHeaders, HttpClient } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';

import { CreateProfileComponent } from './components/create-profile/create-profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PhotoUploadComponent } from './components/photo-upload/photo-upload.component';
import { NavbarComponent } from './components/main/navbar/navbar.component';
import { MatchDetailComponent } from './components/main/match-detail/match-detail.component';
import { ViewHomeComponent } from './components/main/view-home/view-home.component';
import { EditProfileComponent } from './components/main/edit-profile/edit-profile.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';
import { EditPaymentComponent } from './components/edit-payment/edit-payment.component';
import { MatchListComponent } from './components/match-list/match-list.component';
import { PotenMatchListComponent } from './components/poten-match-list/poten-match-list.component';
import { MatchListItemComponent } from './components/match-list-item/match-list-item.component';
import { ProfileCardComponent } from './components/profile-card/profile-card.component';
import { PotenMatchListItemComponent } from './components/poten-match-list-item/poten-match-list-item.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    AppComponent,
    RegisterationComponent,
    PaymentComponent,
    LoginComponent, 
    CreateProfileComponent,
    PhotoUploadComponent,
    NavbarComponent,
    MatchDetailComponent,
    ViewHomeComponent,
    EditProfileComponent,
    ViewProfileComponent,
    EditPaymentComponent,
    MatchListComponent,
    PotenMatchListComponent,
    PotenMatchListItemComponent,
    MatchListItemComponent,
    ProfileCardComponent,
    PhotoUploadComponent
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


