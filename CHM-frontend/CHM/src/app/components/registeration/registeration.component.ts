import { Component, Input, OnInit, ɵɵtrustConstantResourceUrl } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { User } from 'src/app/classes/user';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {

  registrationForm: FormGroup;
  error : string[] = [];

  @Input()
  newUser: User;

  constructor(private userServ: UserService,
              private router: Router) {}

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl('',
          [Validators.required,
            //regex to let user use munbers and characters in username
            Validators.pattern('[a-zA-Z0-9]*')
          ]
      ),
      password: new FormControl('',
        [Validators.required,
        Validators.minLength(8),
        //regex for at least 1 uppercase, one lowercase, one number, one special character
       // Validators.pattern('(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])')

      ]
      )
    });
  }

  onSubmit() {
    const { username, password } = this.registrationForm.value;

    this.newUser = new User();
    this.newUser.username = username;
    this.newUser.password = password;

    /*
    if all the inputs are valid then make call to backend
    */
    if(this.registrationForm.valid){
      this.userServ.createUser(this.newUser).subscribe(
        (user) => {console.log("New User :" +user)},
  
        (errorMessage) => {
          console.log(errorMessage);
        }
      ) ;
  }

   /*
    Pulling the errors of username
    */
    let passwordError = this.registrationForm.controls.password.errors;
    if(passwordError != null){
      if(passwordError.required == true){
        this.error.push("Password is required");
      }
      if(passwordError.minLength != null)
      this.error.push("Password should contain at least 8 characters");
    }

    /*
    Pulling the errors of username
    */
    let usernameError = this.registrationForm.controls.username.errors;
    if(usernameError != null){
      if(usernameError.required == true){
        this.error.push("Username is required");
      }
      if(usernameError.pattern != null)
      this.error.push("Username should contain only alphanumeric characters");
    }

    console.log(this.registrationForm);
    console.log(this.error);

   this.router.navigate(['/signup/profile'])

  }
}
