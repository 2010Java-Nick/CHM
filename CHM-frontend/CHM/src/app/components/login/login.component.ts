import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { HttpErrorResponse } from '../../../../node_modules/@angular/common/http';
import { environment } from 'src/environments/environment';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';

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
  incorrect:boolean;

  loginForm = {} as FormGroup;

  @Output() loginAttempt = new EventEmitter<string>();

  private authHeader = environment.authHeader;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {

    this.loginForm = new FormGroup({
      username: new FormControl(
        '',[Validators.required]
      ),
      password: new FormControl(
        '',[Validators.required]
      ),
      rememberme: new FormControl()
    })
  }

  public submit(): void {
    this.username = this.loginForm.controls.username.value;
    this.password = this.loginForm.controls.password.value;
    this.rememberMe = this.loginForm.controls.rememberme.value;

    if(this.loginForm.valid) {
      this.loginService.login(this.username, this.password, this.rememberMe).subscribe(
        (resp) => {

<<<<<<< HEAD
          this.incorrect = false;
          console.log('In response of submit method');
          this.errorMessage = `successful login`;
          console.log('resp.headers.get(this.authHeader): ' + resp.headers.get(this.authHeader));
=======
        console.log('resp.headers.get(\'ProfileId\') :' + resp.headers.get('ProfileId'));
        this.loginService.setProfileId(resp.headers.get('ProfileId'));

        console.log('In response of submit method');
        this.errorMessage = `successful login`;
        console.log('resp.headers.get(this.authHeader): ' + resp.headers.get(this.authHeader));
>>>>>>> 610c501 (further worked with tokens to allow validations and pulling out claims)

          var myToken = ''
          if(resp.headers.get(this.authHeader) !== null){
            myToken = resp.headers.get(this.authHeader)!;
          }

          this.loginService.setJWT(myToken);

          console.log(this.authHeader + ` : ` + resp.headers.get(this.authHeader));

          console.log('resp.headers' + resp.headers);
          console.log('JWT set to ' + this.loginService.getJWT(new Date().getTime()));

          this.loginAttempt.emit(`success`);

          this.router.navigate(['/home']);
        },
        (error) => {
          console.log('In error of submit method');
          this.handleError(error);
          this.incorrect = true;
          this.loginAttempt.emit(`failed`);
        }
      );
    } else {
      window.alert("Please enter a username / password!");
    }
  
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
