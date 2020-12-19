import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from 'src/app/classes/user';
import { Profile } from '../../classes/profile.model';

import { Injectable } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ProfileService } from '../../services/profile.service';


@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {

  registrationForm: FormGroup;

  profile: Profile = {
    profileId : 1,
    firstName : "bob",
    lastName: "builder",
    email: "builder@email.com",
    phone : "1234567891",
    age : 19,
    bio: "I build",
    icebreaker : "what do you build?",
    primaryInterest : "Cooking"
  }

  //  @Input()
  //  newUser! :  User;
  @Input()
  newUser!: User;

  constructor(private userServ: UserService, private profileServ: ProfileService) {
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

    console.log("before");
    this.profileServ.readAllProfiles();
    console.log("called");
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
