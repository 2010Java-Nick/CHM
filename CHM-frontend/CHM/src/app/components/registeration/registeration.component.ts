import { Component, Input, OnInit } from '@angular/core';
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

  @Input()
  newUser: User;

  constructor(private userServ: UserService,
              private router: Router) {}

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl(
          [Validators.required,
            //regex to let user use munbers and characters in username
            Validators.pattern('[a-zA-Z0-9]*')
          ]
      ),
      password: new FormControl('',
        [Validators.required,
        Validators.minLength(8),
        //regex for at least 1 uppercase, one lowercase, one number, one special character
        Validators.pattern('(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])')

      ]
      )
    });
  }

  onSubmit() {
    const { username, password } = this.registrationForm.value;

    this.newUser = new User();
    this.newUser.username = username;
    this.newUser.password = password;

    this.userServ.createUser(this.newUser).subscribe(
      user => console.log(user)
    ) ;
    
    this.router.navigate(['/signup/profile'])

  }
}
