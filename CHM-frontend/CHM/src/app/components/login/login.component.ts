import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { HttpErrorResponse } from '../../../../node_modules/@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!: string;
  rememberMe!: boolean;
  errorMessage!: string;

  @Output() loginAttempt = new EventEmitter<string>();

  private authHeader = environment.authHeader;

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  public submit(): void {

    this.loginService.login(this.username, this.password, this.rememberMe).subscribe(
      (resp) => {

        console.log('In response of submit method');
        this.errorMessage = `successful login`;
        console.log('resp.headers.get(this.authHeader): ' + resp.headers.get(this.authHeader));

      //  this.loginService.setJWT(resp.headers.get(this.authHeader));

        console.log(this.authHeader + ` : ` + resp.headers.get(this.authHeader));

        console.log('resp.headers' + resp.headers);
        console.log('JWT set to ' + this.loginService.getJWT(new Date().getTime()));

        this.loginAttempt.emit(`success`);
      },
      (error) => {
        console.log('In error of submit method');
        this.handleError(error);
        this.loginAttempt.emit(`failed`);
      }
    );
  }

  private handleError(error: HttpErrorResponse): void {
    console.log(error);
    if (error.status === 404) {
      this.errorMessage = `Sorry, could not find Login`;
    } else if (error.status === 403) {
      this.errorMessage = 'Login credentials provided are inaccurate';
    } else {
      this.errorMessage = `problem with service, sorry try again`;
    }
  }

}
