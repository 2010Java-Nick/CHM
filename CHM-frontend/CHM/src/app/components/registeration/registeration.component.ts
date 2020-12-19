import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from 'src/app/classes/user';

import { Injectable } from '@angular/core';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {

  registrationForm: FormGroup;

  //  @Input()
  //  newUser! :  User;
  @Input()
  newUser!: User;

  constructor(private userServ: UserService) {
    this.registrationForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  onSubmit() {
    const { username, password } = this.registrationForm.value;

    // this.newUser.username = username;
    // this.newUser.password = password;

    this.newUser = new User(username, password);

    console.log(this.newUser.username);

    this.userServ.createUser(this.newUser);

    
  }
}
